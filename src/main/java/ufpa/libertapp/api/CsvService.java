package ufpa.libertapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        List<String> errors = new ArrayList<>(); // Lista de erros
        int lineNumber = 0; // Contador de linhas
        int vitNumber = 0; // Contador de vitimas


        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            // Log para verificar o processamento
            System.out.println("Iniciando o processamento do arquivo CSV...");

            String line;
            boolean isFirstLine = true; // Variável para identificar o cabeçalho

            while ((line = reader.readLine()) != null) {
                lineNumber++; // Incrementa o número da linha

                if (isFirstLine) {
                    // Pula a primeira linha (cabeçalho)
                    isFirstLine = false;
                    continue;
                }

                try {
                    Vitima vitima = csvLineToVitima(line);
                    // Verifica se o CPF já existe no banco de dados
                    if (vitimaRepository.existsById(vitima.getCpf())) {
                        String errorMsg = "Linha " + lineNumber + ": CPF " + vitima.getCpf() + " já cadastrado.";
                        System.out.println(errorMsg); // Log do CPF duplicado

                        errors.add(errorMsg); // Adiciona à lista de erros
                        continue; // Pula o registro se o CPF já existir
                    }
                    vitimaRepository.save(vitima); // Salva o registro
                    vitNumber++;
                } catch (Exception e) {
                    // Adiciona uma mensagem de erro para aquela linha

                    String errorStr = "Erro na linha " + lineNumber + ": " + e.getMessage();
                    errors.add(errorStr);
                }
            }

            // código antigo pra cadastrar vitimas sem retorno de erros
             /*
            // Lê o arquivo linha por linha e mapeia para a entidade Vitima
            List<Vitima> vitimas = reader.lines().skip(1) // Ignora cabeçalho
                    .map(this::csvLineToVitima) // Converte cada linha em um objeto Vitima
                    .collect(Collectors.toList());
            // Log para verificar quantas vítimas foram processadas
            */

            // Salva todas as vítimas no banco de dados
            //vitimaRepository.saveAll(vitimas);

            //System.out.println("Dados salvos no banco de dados.");
            //System.out.println("Número de vítimas processadas: " + vitimas.size());
            System.out.println("Número de vítimas processadas e salvas: " + vitNumber);
            return errors;
        } catch (Exception e) {
            throw new RuntimeException("Error processing CSV file", e);
        }
    }

    // Converte uma linha CSV em um objeto Vitima
    private Vitima csvLineToVitima(String line) throws Exception {

        String[] fields = line.split(",");


        // Verifica se a linha tem o número correto de campos
        if (fields.length < 13) {
            throw new Exception("Linha incompleta. Esperado 13 campos, mas recebido " + fields.length);
        }

        Vitima vitima = new Vitima();
        User user = new User();
        //campos csv cpf[0],nome[1],data_nascimento[2],email[3],cep[4],estado[5],horario[6],
        //rg[7],telefone[8],cidade[9],escolaridade[10],endereco[11],pcd[12]

        vitima.setCpf(fields[0]);
        System.out.println("cpf ok");

        vitima.setNome(fields[1]);
        System.out.println("nome ok");

        //LocalDate data = LocalDate.parse(fields[2], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate data = LocalDate.parse(fields[2]);
        vitima.setData_nascimento(data);
        System.out.println("data ok");

        vitima.setEmail(fields[3]);
        System.out.println("email ok");

        user.setLogin(vitima.getCpf());
        System.out.println("login ok");

        // criacao do usuario e da senha
        StringKeyGenerator generator = KeyGenerators.string();
        String pass = generator.generateKey();
        String limitpass = pass.substring(0, Math.min(6, pass.length()));
        String password = new BCryptPasswordEncoder().encode(limitpass);
        user.setPassword(password);
        user.setRole(UserRole.VITIMA);
        User new_user = userRepository.save(user);

        // armazenar a senha e o token de mudança no banco de dados

        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(tokenService.generatePasswordResetToken(new_user));
        passwordResetToken.setUser(new_user);
        passwordResetToken.setTemp_password(limitpass);
        passwordResetToken.setExpirationTime(LocalDateTime.now().plusHours(730));
        passwordResetTokenRepository.save(passwordResetToken);

        vitima.setUser(new_user);
        System.out.println("user ok");

        vitima.setCep(fields[4]);
        System.out.println("cep ok");

        vitima.setConfirmacao_termo(false);
        System.out.println("confirmacao_termo ok");

        vitima.setEstado(fields[5]);
        System.out.println("estado ok");

        VitimaHorarios horario = VitimaHorarios.valueOf(fields[6].toUpperCase());
        System.out.println("Horario gravado: " + horario.getHorario());
        vitima.setHorario(horario);
        System.out.println("horario ok");

        vitima.setRg(fields[7]);
        System.out.println("rg ok");

        vitima.setTelefone(fields[8]);
        System.out.println("telefone ok");

        vitima.setCidade(fields[9]);
        System.out.println("cidade ok");

        vitima.setEscolaridade(fields[10]);
        System.out.println("escolaridade ok");

        vitima.setCurriculoPdf(null);
        System.out.println("curriculoPdf ok");

        vitima.setEndereco(fields[11]);
        System.out.println("endereco ok");

        vitima.setCursos(null);
        System.out.println("cursos ok");

        vitima.setExperiencias(null);
        System.out.println("experiencias ok");

        vitima.setEmpregada(false);
        System.out.println("empregada ok");

        vitima.setContactada(false);
        System.out.println("Contactada ok");

        vitima.setPcd(fields[12]);
        System.out.println("pcd ok");
        // Preencha outros campos conforme o CSV
        return vitima;
    }

}
