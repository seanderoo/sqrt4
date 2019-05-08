package sqrt4.mijninzet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import sqrt4.mijninzet.model.Users.Docent;
import sqrt4.mijninzet.model.Users.User;
import sqrt4.mijninzet.model.Voorkeur;

@SpringBootApplication
@EnableJpaRepositories
public class MijninzetApplication {

    public static void main(String[] args) {
        SpringApplication.run(MijninzetApplication.class, args);
        Docent docent = new Docent();
        }
    }

