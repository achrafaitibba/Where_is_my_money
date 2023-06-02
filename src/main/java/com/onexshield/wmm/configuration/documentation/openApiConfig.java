package com.onexshield.wmm.configuration.documentation;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
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
                description = "<b>Documentation for 'where is my money' API:</b> <br>" +
                        "This is a sample application that provides a comprehensive <br>" +
                        "demonstration of tracking life transactions to help users <br>" +
                        "understand or remember where their money is spent and where it comes from.<br>" +
                        " The app comes with intuitive visualization(You will find the front end in my github repo)<br>" +
                        " tools to enable users to get a clear picture of their expenses/incomes<br>" +
                        " and make informed decisions.",
                title = "Where is my money API",
                version = "1.0",
                license = @License(
                        name = "MIT License",
                        url = "https://github.com/achrafaitibba/where_is_my_money/blob/master/LICENSE.md"
                )
        ),
        servers = {
                @Server(
                        description = "PROD/test ENV",
                        url = "https://whereismymoney-production-9d7f.up.railway.app"
                ),
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
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
