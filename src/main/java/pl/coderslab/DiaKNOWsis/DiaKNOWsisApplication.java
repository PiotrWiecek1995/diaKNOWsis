package pl.coderslab.DiaKNOWsis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "pl.coderslab.DiaKNOWsis.repository")
public class DiaKNOWsisApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiaKNOWsisApplication.class, args);
    }
}
