package sqrt4.mijninzet.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sqrt4.mijninzet.model.*;
import sqrt4.mijninzet.model.Beschikbaarheid.Cohort;
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
    private CohortRepository cohortRepository;
    private DagdeelRespository dagdeelRespository;

    public DatabaseInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder,
                               VacatureRepository vacatureRepository, VakRepository vakRepository,
                               RoleRepository roleRepository, CohortRepository cohortRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.vacatureRepository = vacatureRepository;
        this.vakRepository = vakRepository;
        this.roleRepository = roleRepository;
        this.cohortRepository = cohortRepository;
    }

    @Override
    public void run(String... args) throws Exception{
        //Delete all users, vacatures, vakken om met schone lei te beginnen
        this.userRepository.deleteAll();
        this.vacatureRepository.deleteAll();
        this.vakRepository.deleteAll();
        this.roleRepository.deleteAll();
        this.cohortRepository.deleteAll();
        this.dagdeelRespository.deleteAll();


        //create rollen
        Role god = new Role("Admin");
        Role docent = new Role("Docent");
        Role manager = new Role("Manager");
        Role roosteraar = new Role("Roosteraar");
        Role administrator = new Role("God");

        List<Role> rollen = Arrays.asList(god, docent, manager, roosteraar, administrator);

        //create users
        User matthijs = new User("M", passwordEncoder.encode("M123"), "DOCENT", "", "Matthijs", "Verkaaik");
        User admin = new User("Admin", passwordEncoder.encode("Admin123"), "ADMIN", "", "Adje", "de Admin");
        User manager1 = new User("Manager", passwordEncoder.encode("Manager123"), "MANAGER", "", "M", "Anager");
        User roosteraar1 = new User("R", passwordEncoder.encode("R123"), "ROOSTERAAR", "", "R", "Oosteraar");


        List<User> users = Arrays.asList(matthijs, admin, manager1, roosteraar1);

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
        Vak programming = new Vak("Programming", 16);
        Vak oop = new Vak("OOP", 16);
        Vak projectQuizmaster = new Vak("Project Quizmaster", 12);

        List<Vak> vakken = Arrays.asList(programming, oop, projectQuizmaster);

        //Create Cohort
        Cohort cohort = new Cohort(20,20,2020,21);

        //save to db
        this.userRepository.saveAll(users);
        this.vacatureRepository.saveAll(vacatures);
        this.vakRepository.saveAll(vakken);
        for (Vak vak:vakken) {
            vak.setDagdelen(vak.aantalDagdelenBerekenen());
            for (Dagdeel dagdeel:vak.getDagdelen()) {
                this.dagdeelRespository.save(dagdeel);
            }
        }
        this.roleRepository.saveAll(rollen);
        this.cohortRepository.save(cohort);

    }
}
