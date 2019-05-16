package sqrt4.mijninzet.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.model.Vacature;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private VacatureRepository vacatureRepository;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder, VacatureRepository vacatureRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder= passwordEncoder;
        this.vacatureRepository = vacatureRepository;
    }

    @Override
    public void run(String... args) throws Exception{
        //Delete all users
        this.userRepository.deleteAll();

        //create users
        User matthijs = new User("M", passwordEncoder.encode("M123"), "USER","");
        User admin = new User("Admin",passwordEncoder.encode("Admin123"),"ADMIN","");

        List<User> users = Arrays.asList(matthijs, admin);



        //Delete all vacatures
        this.vacatureRepository.deleteAll();

        //Create vacatures
        Vacature programming = new Vacature("Programming", "Introductie in Java Programming",
                40, "Kennis van Java");
        Vacature oop = new Vacature("OOP", "Introductie in Object Oriented Programming",
                40, "Kennis van Object Oriented Programming");
        Vacature projectQuizmaster = new Vacature("Project Quizmaster",
                "Introductie projectmatig werken", 60, "Kennis van projectmatig werken");
        List<Vacature> vacatures = Arrays.asList(programming, oop, projectQuizmaster);

        //save to db
        this.userRepository.saveAll(users);
        this.vacatureRepository.saveAll(vacatures);

    }
}
