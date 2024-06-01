package alchem.app;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AppApplication {

    public static void main(String... args) {
        var ignore = new SpringApplicationBuilder()
                .sources(AppApplication.class)
                .web(AppEnv.isUI() ? WebApplicationType.SERVLET : WebApplicationType.NONE)
                .headless(!AppEnv.isUI())
                .run(args);
    }
}
