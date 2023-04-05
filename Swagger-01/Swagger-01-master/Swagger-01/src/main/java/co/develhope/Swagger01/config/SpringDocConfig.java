package co.develhope.Swagger01.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info =
        @Info(title = "Swagger-01", version = "1.0", description = "1st swagger exercise"),
        tags = {
                @Tag(name = "name-controller", description = "The name controller that returns my name"),
                @Tag(name = "default-controller", description = "The default controller that welcomes the user")}
        )
@Configuration
public class SpringDocConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/public/**")
                .build();
    }
}
