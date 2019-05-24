package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.repository.UserRepository;

public abstract class AbstractController {
    @Autowired
    UserRepository userRepo;

    User voegActiveUserToe(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        return userRepo.findByUsername(userName);
    }
}
