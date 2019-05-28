package sqrt4.mijninzet.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sqrt4.mijninzet.model.Role;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.model.Vacature;
import sqrt4.mijninzet.model.Vak;

import java.util.Arrays;
import java.util.List;

@Service
public class DatabaseInitializer implements CommandLineRunner {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private VacatureRepository vacatureRepository;
    private VakRepository vakRepository;
    private RoleRepository roleRepository;

    public DatabaseInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder,
                               VacatureRepository vacatureRepository, VakRepository vakRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.vacatureRepository = vacatureRepository;
        this.vakRepository = vakRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception{
        //Delete all users, vacatures, vakken om met schone lei te beginnen
        this.userRepository.deleteAll();
        this.vacatureRepository.deleteAll();
        this.vakRepository.deleteAll();
        this.roleRepository.deleteAll();


        //create rollen
        Role god = new Role("Admin");
        Role docent = new Role("Docent");
        Role manager = new Role("Manager");
        Role coordinator = new Role("Coordinator");
        Role administrator = new Role("God");

        List<Role> rollen = Arrays.asList(god, docent, manager, coordinator, administrator);

        //create users
        User matthijs = new User("M", passwordEncoder.encode("M123"), "DOCENT", "", "Matthijs", "Verkaaik");
        User admin = new User("Admin", passwordEncoder.encode("Admin123"), "ADMIN", "", "Adje", "de Admin");
        User manager1 = new User("Manager", passwordEncoder.encode("Manager123"), "MANAGER", "", "M", "Anager");


        List<User> users = Arrays.asList(matthijs, admin, manager1);

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
        this.roleRepository.saveAll(rollen);

    }
}
