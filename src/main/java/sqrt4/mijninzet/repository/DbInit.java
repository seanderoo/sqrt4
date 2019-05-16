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
        Vacature coordinatorC16 = new Vacature("Coordinator Cohort 15",
                "De boel coördineren",
                40, "Uitstekende probleemoplossende en prioriterende vaardigheden");
        Vacature coordinatorC17 = new Vacature("Coordinator Cohort 16",
                "De boel coördineren",
                40, "Uitstekende probleemoplossende en prioriterende vaardigheden");
        Vacature productOwnerMijnInzet = new Vacature("Product Owner - Project Mijn Inzet",
                "Het product ownen", 60, "Huub zijn");

        List<Vacature> vacatures = Arrays.asList(coordinatorC16, coordinatorC17, productOwnerMijnInzet);

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
