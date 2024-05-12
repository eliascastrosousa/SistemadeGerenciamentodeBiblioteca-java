package dev.eliascastro.sgb.infra.springdoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocConfigurations {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Sistema de Gerenciamento de Biblioteca")
                        .description("API Rest da aplicação SGB, contendo as funcionalidades de CRUD de alunos e livros, " +
                                "além de emprestimo e devolução de livros")
                        .contact(new Contact()
                                .name("Time Backend")
                                .email("backend@sgb.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://sgb.com/api/licenca")));
    }


}
