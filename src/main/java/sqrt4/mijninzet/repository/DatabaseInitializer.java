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
    private VakdagdeelRespository vakdagdeelRespository;

    public DatabaseInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder,
                               VacatureRepository vacatureRepository, VakRepository vakRepository,
                               RoleRepository roleRepository, CohortRepository cohortRepository, VakdagdeelRespository vakdagdeelRespository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.vacatureRepository = vacatureRepository;
        this.vakRepository = vakRepository;
        this.roleRepository = roleRepository;
        this.cohortRepository = cohortRepository;
        this.vakdagdeelRespository = vakdagdeelRespository;
    }

    @Override
    public void run(String... args) throws Exception{
        //Delete all users, vacatures, vakken om met schone lei te beginnen
        this.userRepository.deleteAll();
        this.vacatureRepository.deleteAll();
        this.vakRepository.deleteAll();
        this.roleRepository.deleteAll();
        this.cohortRepository.deleteAll();
        this.vakdagdeelRespository.deleteAll();


        //create rollen
        Role god = new Role("Admin");
        Role docent = new Role("Docent");
        Role coordinator = new Role("Coordinator");
        Role roosteraar = new Role("Roosteraar");
        Role administrator = new Role("God");

        List<Role> rollen = Arrays.asList(god, docent, coordinator, roosteraar, administrator);

        //create users
        User matthijs = new User("M", passwordEncoder.encode("M123"), "DOCENT", "", "Matthijs", "Verkaaik", "");
        User huub = new User("H", passwordEncoder.encode("H123"), "DOCENT", "", "Huub", "van Thienen", "");
        User gerke = new User("G", passwordEncoder.encode("G123"), "DOCENT", "", "Gerke", "de Boer", "");
        User lillian = new User("L", passwordEncoder.encode("L123"), "DOCENT", "", "Lilian", "Smits", "");
        User remi = new User("Re", passwordEncoder.encode("R123"), "DOCENT", "", "Remi", "de Boer", "");
        User ronald = new User("Ro", passwordEncoder.encode("Ro123"), "DOCENT", "", "Ronald", "Kleijn", "");
        User admin = new User("Admin", passwordEncoder.encode("Admin123"), "ADMIN,DOCENT", "", "Adje", "de Admin", "");
        User coordinator1 = new User("Co", passwordEncoder.encode("C123"), "COORDINATOR", "", "Co", "Ordinator", "");
        User roosteraar1 = new User("R", passwordEncoder.encode("R123"), "ROOSTERAAR", "", "R", "Oosteraar", "");


        List<User> users = Arrays.asList(matthijs, huub, gerke, lillian, remi, ronald, admin, coordinator1, roosteraar1);

        //Create vacatures
        Vacature coordinatorC15 = new Vacature("Coordinator Cohort 15",
                "Als coordinator moet je de boel coördineren. ",
                40, "Uitstekende probleemoplossende en prioriterende vaardigheden");
        Vacature examen = new Vacature("Examencommissie",
                "Voorzitter zijn van de examencommissie. Zorgen voor de verspreiding van de tentamens. Tevens " +
                        "zorg je ervoor dat alles wordt nagekeken en dat de studenten geinformeerd worden over hun cijfer",
                55, "In bezit zijn van certificaat Examen afnemen. Op maandagen beschikbaar zijn");
        Vacature productOwnerMijnInzet = new Vacature("Product Owner - Project Mijn Inzet",
                "Het product ownen zodat je iedereen kunt vertellen wat er van hen verwacht wordt", 60, "Huub zijn");
        Vacature orkestleider = new Vacature("Orkestleider HvA Orkest", "Je bent de orkestleider van het HvA huisorkest dus " +
                "je reist mee als het orkest speelt. Je beslist mee over het repertoire en neemt nieuwe musici aan.", 35, "Muzikaal zijn." +
                " In het bezit zijn van een muziekinstrument.");

        List<Vacature> vacatures = Arrays.asList(coordinatorC15, examen, productOwnerMijnInzet, orkestleider);

        //Create vakken
        Vak geenLes = new Vak("Geen les", 0);
        Vak programming = new Vak("Programming", 36);
        Vak database = new Vak("Databases", 48);
        Vak oop = new Vak("OOP", 48);
        Vak projectQuizmaster = new Vak("Project Quizmaster", 12);
        Vak advanced = new Vak("Advanced Programming", 30);
        Vak wsUML = new Vak ("WS UML", 4);
        Vak wsMVC  = new Vak ("WS MVC", 4);
        Vak wsGIT   = new Vak("WS GIT", 4);
        Vak wsTesting = new Vak("WS Testing", 4);
        Vak wsWeb = new Vak ("WS HTML/CSS/JS", 4);
        Vak wsNoSql = new Vak("WS NoSQL & CouchDB", 4);
        Vak voortgangsgesprekken = new Vak("Voortgangsgesprekken", 16);
        Vak projectbegeleiding = new Vak("Projectbegeleiding", 80);

        List<Vak> vakken = Arrays.asList(geenLes, programming, database, oop,
                projectQuizmaster, advanced, wsUML, wsGIT, wsMVC, wsTesting, wsWeb, wsNoSql,
                voortgangsgesprekken, projectbegeleiding);

        //Create Cohort
        Cohort cohort15 = new Cohort(15,6,2019,27);
        Cohort cohort16 = new Cohort(16, 16, 2019,37);
        Cohort cohort17 = new Cohort(17, 26, 2019, 47);
        Cohort cohort18 = new Cohort(18, 36, 2019, 3);
        Cohort cohort19 = new Cohort(19, 46, 2019, 13);
        Cohort cohort20 = new Cohort(20, 4, 2020, 23);
        List<Cohort> cohorts = Arrays.asList(cohort15, cohort16, cohort17, cohort18, cohort19, cohort20);

        //save to db
        this.userRepository.saveAll(users);
        this.vacatureRepository.saveAll(vacatures);
        this.vakRepository.saveAll(vakken);
        for (Vak vak:vakken) {
            vak.setVakdagdelen(vak.aantalDagdelenBerekenen());
            for (Vakdagdeel vakdagdeel :vak.getVakdagdelen()) {
                this.vakdagdeelRespository.save(vakdagdeel);
            }
        }
        this.roleRepository.saveAll(rollen);
        this.cohortRepository.saveAll(cohorts);

    }
}
