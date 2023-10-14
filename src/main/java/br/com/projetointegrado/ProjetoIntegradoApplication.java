package br.com.projetointegrado;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Projeto Integrado",
                version = "1",
                description = "API de funcionalidades para o Projeto Integrado",
                contact = @Contact(
                        name = "Wagner Martins Santana Vieira",
                        url = "https://www.linkedin.com/in/wagner-martins-santana/",
                        email = "wagnermartinssantana@gmail.com"
                )
        )
)
public class ProjetoIntegradoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetoIntegradoApplication.class, args);
    }
}