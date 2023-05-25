package com.onexshield.wmm.authentication_configuration.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Achraf Ait Ibba",
                        email = "aitibbaachraf@gmail.com",
                        url = "https://achrafaitibba.com/wmm"
                ),
                description = "Documentation for 'where is my money' API: " +
                        "This is a sample application that provides" +
                        " a comprehensive demonstration of tracking life transactions" +
                        " to help users understand or remember where their money is spent and where it comes from." +
                        " The app comes with intuitive visualization(User Interface) tools to enable users" +
                        " to get a clear picture of their expenses/incomes" +
                        " and make informed decisions.",
                title = "Where is my money API",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Dev/test ENV",
                        url = "https://achrafaitibba.com"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT authentication",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class openApiConfig {
}
