package sqrt4.mijninzet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sqrt4.mijninzet.model.Beschikbaarheid.Semester;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AlgemeneBeschikbaarheidController {

    @GetMapping("/algemene-beschikbaarheid")
    public String AlgemeneBeschikbaarheid() {

        return "algemene-beschikbaarheid";
    }

    @ModelAttribute("semesters")
    public List<Semester> semesters() {
        ArrayList<Semester> semesterlijst = new ArrayList<>();
        semesterlijst.add(new Semester(4,2020,29));
        semesterlijst.add(new Semester(30, 2020, 3));
        return semesterlijst;
    }
//---------------------------------------------------------------
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public ModelAndView save(@ModelAttribute Week week){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("week", week);
//        return modelAndView;
//    }
}
