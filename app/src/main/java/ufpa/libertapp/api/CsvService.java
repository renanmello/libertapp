package ufpa.libertapp.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ufpa.libertapp.user.User;
import ufpa.libertapp.user.UserRepository;
import ufpa.libertapp.user.UserRole;
import ufpa.libertapp.vitima.Vitima;
import ufpa.libertapp.vitima.VitimaRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CsvService {

    private final VitimaRepository vitimaRepository;
    private final UserRepository userRepository;


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
        // campos csv cpf,nome,data_nascimento,email,cep,estado,cidade,escolaridade,endereco,pcd
        vitima.setCpf(fields[0]);
        System.out.println("cpf ok");
        vitima.setNome(fields[1]);
        System.out.println("nome ok");
        Date data = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(fields[2]);
            vitima.setData_nascimento(date);
        } catch (Exception e) {
            System.out.println(e);
        }


        System.out.println("data ok");
        vitima.setEmail(fields[3]);
        System.out.println("email ok");
        if (vitima.getEmail() == null) {
            user.setLogin(vitima.getCpf());
        } else {
            user.setLogin(vitima.getEmail());
        }
        String password = new BCryptPasswordEncoder().encode("vit1234");
        user.setPassword(password);
        user.setRole(UserRole.VITIMA);
        User new_user = userRepository.save(user);

        vitima.setUser(new_user);
        System.out.println("user ok");

        vitima.setCep(fields[4]);
        System.out.println("cep ok");
        vitima.setConfirmacao_termo(false);

        vitima.setEstado(fields[5]);
        System.out.println("estadi ok");
        vitima.setCidade(fields[6]);
        System.out.println("cidade ok");
        vitima.setEscolaridade(fields[7]);
        System.out.println("escolaridade ok");
        vitima.setCurriculoPdf(null);
        vitima.setEndereco(fields[8]);
        System.out.println("endereco ok");
        vitima.setCursos(null);
        vitima.setExperiencias(null);
        vitima.setEmpregada(false);
        vitima.setPcd(fields[9]);
        System.out.println("pcd ok");
        // Preencha outros campos conforme o CSV
        return vitima;
    }
    /*
        "cpf": "1121123337", check
        "nome": "teste joao", check
        "data_nascimento": "2020-01-01", check
        "pcd": 0, check
        "estado": "PA", check
        "cidade": "belem", check
        "cep": "655543", check
        "endereco": "rua batatinha", check
        "escolaridade":  "fundamental incompleto", check
        "experiencias": null, check
        "cursos": null, check
        "curriculoPdf": null, check
        "confirmacao_termo": 0, check
        "empregada": 0 check
*/
}
