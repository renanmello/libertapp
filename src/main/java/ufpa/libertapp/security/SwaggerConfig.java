package ufpa.libertapp.security;


import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do Swagger para documentação da API.
 * <p>
 * Esta classe configura o Swagger para agrupar e documentar endpoints públicos
 * da aplicação, permitindo uma documentação automática e organizada.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@Configuration
public class SwaggerConfig {
    /**
     * Define um grupo de API pública para documentar todos os endpoints.
     *
     * @return uma instância configurada de {@link GroupedOpenApi} para o grupo público
     */
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }
}