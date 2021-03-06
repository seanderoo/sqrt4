package sqrt4.mijninzet.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.repository.UserRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public UserPrincipalDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException(s);
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        String[] authStrings = user.getRoles().split(",");
        for(String authString : authStrings) {
            authorities.add(new SimpleGrantedAuthority(authString));
        }

        return new UserPrincipal(user);
    }
}
