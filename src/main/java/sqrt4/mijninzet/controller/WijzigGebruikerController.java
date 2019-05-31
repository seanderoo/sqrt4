package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sqrt4.mijninzet.model.Role;
import sqrt4.mijninzet.repository.RoleRepository;

import java.util.List;

@RequestMapping("/admin")
@Controller
public class WijzigGebruikerController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/wijzig-gebruiker")
    public String getWijzigGebruiker(Model model) {
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("roles", roles);
        return "wijzig-gebruiker";
    }
}

