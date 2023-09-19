package de.db.product.tradingapplication.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
@OpenAPIDefinition(info = @Info(contact = @Contact(
    name = "Abdallah Emad",
    email = "abdallah.coder@gmail.com",
    url = "https://www.abdallah-coder.com"),
    description = "OpenApi documentation for trading application API",
    title = "Trading Application API",
    version = "1.0",
    license = @License(
        name = "trading application Licence",
        url = "https://www.trading.com/en/info/privacy-policy"),
    termsOfService = "https://www.trading.com/en/info/terms-of-use"),
    servers = {
        @Server(description = "LOCAL Environment", url = "http://localhost:8080"),
        @Server(description = "PROD Environment", url = "https://trading.com/") },
    security = {
        @SecurityRequirement(name = "bearerAuth") })
@SecurityScheme(name = "bearerAuth", description = "JWT auth description", scheme = "bearer", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", in = SecuritySchemeIn.HEADER)
public class OpenApiConfig {

}