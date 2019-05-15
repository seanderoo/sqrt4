package sqrt4.mijninzet.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sqrt4.mijninzet.model.User;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder= passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception{
        //Delete all
        this.userRepository.deleteAll();

        //create users
        User matthijs = new User("M", passwordEncoder.encode("M123"), "USER","");
        User admin = new User("Admin",passwordEncoder.encode("Admin123"),"ADMIN","");

        List<User> users = Arrays.asList(matthijs, admin);

        //save to db
        this.userRepository.saveAll(users);
    }
}
