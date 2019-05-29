package sqrt4.mijninzet.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/voorkeuren")
public class AlgemeneController {

    @RequestMapping(value="/{user}/{vak}")
    public String testRest(@PathVariable String user, String vak) {

        return "mies";
    }



}
