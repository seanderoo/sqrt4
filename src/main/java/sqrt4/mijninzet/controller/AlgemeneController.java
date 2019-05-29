package sqrt4.mijninzet.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlgemeneController {

    @RequestMapping(value="/voorkeuren/{echo}")
    public String testRest(@PathVariable String echo) {
        if (echo.equals("1")) {
            return "noot";
        }
        return "mies";
    }

}
