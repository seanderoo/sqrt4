package sqrt4.mijninzet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AlgemeneBeschikbaarheidController {

    @GetMapping("/algemene-beschikbaarheid")
    public String AlgemeneBeschikbaarheid(Model model, @RequestParam(value = "name", required = false,
            defaultValue = "Karin") String name) {
        model.addAttribute("name", name);


        return "algemene-beschikbaarheid";
    }
}
//---------------------------------------------------------------
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public ModelAndView save(@ModelAttribute Week week){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("week", week);
//        return modelAndView;
//    }
//}
