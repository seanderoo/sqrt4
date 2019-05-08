package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import repository.UserRepository;

@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;
}
