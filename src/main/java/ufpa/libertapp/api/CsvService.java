package ufpa.libertapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.web.multipart.MultipartFile;
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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CsvService {
    private final VitimaRepository vitimaRepository;
    private final UserRepository userRepository;

    @Autowired
    public CsvService(VitimaRepository vitimaRepository, UserRepository userRepository) {
        this.vitimaRepository = vitimaRepository;
        this.userRepository = userRepository;
    }

    public void save(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            // Log para verificar o processamento
            System.out.println("Iniciando o processamento do arquivo CSV...");

            // Lê o arquivo linha por linha e mapeia para a entidade Vitima
            List<Vitima> vitimas = reader.lines().skip(1) // Ignora cabeçalho
                .map(this::csvLineToVitima) // Converte cada linha em um objeto Vitima
                .collect(Collectors.toList());
            // Log para verificar quantas vítimas foram processadas
            System.out.println("Número de vítimas processadas: " + vitimas.size());

            // Salva todas as vítimas no banco de dados
            vitimaRepository.saveAll(vitimas);

            System.out.println("Dados salvos no banco de dados.");
        } catch (Exception e) {
            throw new RuntimeException("Error processing CSV file", e);
        }
    }

    // Converte uma linha CSV em um objeto Vitima
    private Vitima csvLineToVitima(String line) {
        String[] fields = line.split(",");
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

        StringKeyGenerator generator = KeyGenerators.string();
        String pass = generator.generateKey();
        String limitpass = pass.substring(0, Math.min(6, pass.length()));
        String password = new BCryptPasswordEncoder().encode(limitpass);
        user.setPassword(password);
        user.setRole(UserRole.VITIMA);
        User new_user = userRepository.save(user);

        vitima.setUser(new_user);
        System.out.println("user ok");

        vitima.setCep(fields[4]);
        System.out.println("cep ok");

        vitima.setConfirmacao_termo(false);
        System.out.println("confirmacao_termo ok");

        vitima.setEstado(fields[5]);
        System.out.println("estado ok");

        VitimaHorarios horario = VitimaHorarios.valueOf(fields[6].toUpperCase());
        System.out.println("Horario gravado: "+horario.getHorario());
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
