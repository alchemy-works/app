package alchem.app;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> serverPortCustomizer() {
        return (factory) -> factory.setPort(0);
    }

    @Bean
    public ApplicationRunner runner(@NotNull AppUI ui) {
        return (args) -> {
            switch (AppEnv.getAppType()) {
                case AppEnv.CLI -> {
                    Console.log(Arrays.toString(args.getSourceArgs()));
                    System.exit(0);
                }
                case AppEnv.UI -> ui.start();
                default -> Console.error("Unknown APP");
            }
        };
    }
}
