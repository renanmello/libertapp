package ufpa.libertapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ufpa.libertapp.passwordresettoken.PasswordResetToken;
import ufpa.libertapp.passwordresettoken.PasswordResetTokenRepository;
import ufpa.libertapp.security.TokenService;
import ufpa.libertapp.user.User;
import ufpa.libertapp.user.UserRepository;
import ufpa.libertapp.user.UserRole;
import ufpa.libertapp.vitima.Vitima;
import ufpa.libertapp.vitima.VitimaHorarios;
import ufpa.libertapp.vitima.VitimaRepository;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class CsvService {

    private final VitimaRepository vitimaRepository;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    public CsvService(VitimaRepository vitimaRepository, UserRepository userRepository, TokenService tokenService, PasswordResetTokenRepository passwordResetTokenRepository) {
        this.vitimaRepository = vitimaRepository;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    public List<String> save(MultipartFile file) {
        List<String> errors = new ArrayList<>();
        int lineNumber = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            System.out.println("Iniciando o processamento do arquivo CSV...");
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] fields = line.split(",");
                StringBuilder lineErrors = new StringBuilder("Erro na linha " + lineNumber + ": ");

                try {
                    if (fields.length < 13) {
                        errors.add(lineErrors.append("Linha incompleta. Esperado 13 campos, mas recebido ").append(fields.length).toString());
                        continue;
                    }

                    if (fields[0].isEmpty()) lineErrors.append("CPF está vazio. ");
                    if (fields[1].isEmpty()) lineErrors.append("Nome está vazio. ");
                    if (fields[2].isEmpty()) lineErrors.append("Data de nascimento está vazia. ");
                    if (fields[3].isEmpty()) lineErrors.append("Email está vazio. ");
                    if (fields[4].isEmpty()) lineErrors.append("CEP está vazio. ");
                    if (fields[5].isEmpty()) lineErrors.append("Estado está vazio. ");
                    if (fields[6].isEmpty()) lineErrors.append("Horário está vazio. ");
                    if (fields[7].isEmpty()) lineErrors.append("RG está vazio. ");
                    if (fields[8].isEmpty()) lineErrors.append("Telefone está vazio. ");
                    if (fields[9].isEmpty()) lineErrors.append("Cidade está vazia. ");
                    if (fields[10].isEmpty()) lineErrors.append("Escolaridade está vazia. ");
                    if (fields[11].isEmpty()) lineErrors.append("Endereço está vazio. ");
                    if (fields[12].isEmpty()) lineErrors.append("PCD está vazio. ");

                    if (lineErrors.length() > ("Erro na linha " + lineNumber + ": ").length()) {
                        errors.add(lineErrors.toString());
                        continue; // Pula o registro se houver campos obrigatórios vazios
                    }

                    if (vitimaRepository.existsById(fields[0])) {
                        errors.add("Linha " + lineNumber + ": CPF " + fields[0] + " já cadastrado.");
                        continue;
                    }

                    // Criação de usuário e senha
                    User user = new User();
                    user.setLogin(fields[0]);
                    String tempPassword = KeyGenerators.string().generateKey().substring(0, 6);
                    String encodedPassword = new BCryptPasswordEncoder().encode(tempPassword);
                    user.setPassword(encodedPassword);
                    user.setRole(UserRole.VITIMA);
                    User savedUser = userRepository.save(user);

                    // Criação do token de redefinição de senha
                    PasswordResetToken passwordResetToken = new PasswordResetToken();
                    passwordResetToken.setToken(tokenService.generatePasswordResetToken(savedUser));
                    passwordResetToken.setUser(savedUser);
                    passwordResetToken.setTemp_password(tempPassword);
                    passwordResetToken.setExpirationTime(LocalDateTime.now().plusHours(730));
                    PasswordResetToken savedToken = passwordResetTokenRepository.save(passwordResetToken);

                    try {
                        // Criação e associação de vítima ao usuário
                        Vitima vitima = new Vitima();
                        vitima.setCpf(fields[0]);
                        vitima.setNome(fields[1]);
                        vitima.setData_nascimento(LocalDate.parse(fields[2]));
                        vitima.setEmail(fields[3]);
                        vitima.setCep(fields[4]);
                        vitima.setEstado(fields[5]);
                        vitima.setHorario(VitimaHorarios.valueOf(fields[6].toUpperCase()));
                        vitima.setRg(fields[7]);
                        vitima.setTelefone(fields[8]);
                        vitima.setCidade(fields[9]);
                        vitima.setEscolaridade(fields[10]);
                        vitima.setEndereco(fields[11]);
                        vitima.setPcd(fields[12]);
                        vitima.setUser(savedUser);

                        vitimaRepository.save(vitima);

                    } catch (Exception e) {
                        userRepository.delete(savedUser);
                        passwordResetTokenRepository.delete(savedToken);
                        errors.add("Erro na linha " + lineNumber + ": Falha ao salvar a vítima - " + e.getMessage());
                    }

                } catch (Exception e) {
                    errors.add("Erro na linha " + lineNumber + ": " + e.getMessage());
                }
            }

            return errors;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar o arquivo CSV: " + e.getMessage());
        }
    }
}
