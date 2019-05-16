package sqrt4.mijninzet.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.model.Vacature;
import sqrt4.mijninzet.model.Vak;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private VacatureRepository vacatureRepository;
    private VakRepository vakRepository;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder,
                  VacatureRepository vacatureRepository, VakRepository vakRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.vacatureRepository = vacatureRepository;
        this.vakRepository = vakRepository;
    }

    @Override
    public void run(String... args) throws Exception{
        //Delete all users, vacatures, vakken om met schone lei te beginnen
        this.userRepository.deleteAll();
        this.vacatureRepository.deleteAll();
        this.vakRepository.deleteAll();


        //create users
        User matthijs = new User("M", passwordEncoder.encode("M123"), "USER","");
        User admin = new User("Admin",passwordEncoder.encode("Admin123"),"ADMIN","");

        List<User> users = Arrays.asList(matthijs, admin);

        //Create vacatures
        Vacature programmingVacature = new Vacature("Programming", "Introductie in Java Programming",
                40, "Kennis van Java");
        Vacature oopVavature = new Vacature("OOP", "Introductie in Object Oriented Programming",
                40, "Kennis van Object Oriented Programming");
        Vacature projectQuizmasterVacature = new Vacature("Project Quizmaster",
                "Introductie projectmatig werken", 60, "Kennis van projectmatig werken");

        List<Vacature> vacatures = Arrays.asList(programmingVacature, oopVavature, projectQuizmasterVacature);

        //Create vakken
        Vak programming = new Vak("Programming", 40);
        Vak oop = new Vak("OOP", 40);
        Vak projectQuizmaster = new Vak("Project Quizmaster", 60);

        List<Vak> vakken = Arrays.asList(programming, oop, projectQuizmaster);

        //save to db
        this.userRepository.saveAll(users);
        this.vacatureRepository.saveAll(vacatures);
        this.vakRepository.saveAll(vakken);
    }
}
