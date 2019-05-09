package sqrt4.mijninzet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AlgemeneBeschikbaarheidController {

    @GetMapping("/algemene-beschikbaarheid")
    public String AlgemeneBeschikbaarheid() {

        return "algemene-beschikbaarheid";
    }
//---------------------------------------------------------------
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public ModelAndView save(@ModelAttribute Week week){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("week", week);
//        return modelAndView;
//    }
}
