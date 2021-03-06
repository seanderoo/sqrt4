package sqrt4.mijninzet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sqrt4.mijninzet.model.Beschikbaarheid.Dagdeel;
import sqrt4.mijninzet.model.Beschikbaarheid.Week;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.model.Vak;
import sqrt4.mijninzet.model.Voorkeur;
import sqrt4.mijninzet.repository.DagdeelRepository;
import sqrt4.mijninzet.repository.UserRepository;
import sqrt4.mijninzet.repository.VakRepository;
import sqrt4.mijninzet.repository.VoorkeurenRepository;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ajax")
public class AlgemeneController extends AbstractController {

    @Autowired
    private VoorkeurenRepository voorkeurenRepository;
    @Autowired
    private VakRepository vakRepository;

    @RequestMapping(value = "/voorkeuren/{user}/{vak}", method = RequestMethod.POST)
    public Integer testRest(@PathVariable User user, @PathVariable Vak vak) {
        Integer preference = 0;
        try {
            Integer databaseVoorkeur = voorkeurenRepository.findVoorkeurByVakAndUser(vak, user).getVoorkeurGebruiker();
            if (databaseVoorkeur == null) {
                return 0;
            } else {
                return databaseVoorkeur;
            }
        } catch (NullPointerException e) {
        }
        return preference;
    }

    @RequestMapping(value = "/voorkeuren/{user}", method = RequestMethod.GET)
    public List<Voorkeur> showAllPreferences(@PathVariable User user) {
        List<Voorkeur> voorkeuren = voorkeurenRepository.findAllByUser(user);
        return voorkeuren;
    }

    @RequestMapping(value = "/{user}", method = RequestMethod.POST)
    public String testRest2(@PathVariable User user) {
        return "";
    }

    @RequestMapping(value = "/roosteraar/docent-koppelen-gekozen-cohort/{weekId}", method = RequestMethod.POST)
    public Week getAantalWekenGeselecteerdeCohort(@PathVariable int weekId) {
        Week week = null;


        return week;
    }

    //haalt de vakken op die al voor die week zijn ogeslagen (James en Karin)
    @RequestMapping(value = "/coordinator/rooster-maken-cohort-gekozen-karin/{week}", method = RequestMethod.GET)
    public List<String> vakAlOpgeslagen(@PathVariable Week week) {
        String maOcht = week.getMaandag().getOchtend().getVak().getVakNaam();
        String maMid = week.getMaandag().getMiddag().getVak().getVakNaam();
        String maAvo = week.getMaandag().getAvond().getVak().getVakNaam();
        String diOcht = week.getDinsdag().getOchtend().getVak().getVakNaam();
        String diMid = week.getDinsdag().getMiddag().getVak().getVakNaam();
        String diAvo = week.getDinsdag().getAvond().getVak().getVakNaam();
        String woOcht = week.getWoensdag().getOchtend().getVak().getVakNaam();
        String woMid = week.getWoensdag().getMiddag().getVak().getVakNaam();
        String woAvo = week.getWoensdag().getAvond().getVak().getVakNaam();
        String doOcht = week.getDonderdag().getOchtend().getVak().getVakNaam();
        String doMid = week.getDonderdag().getMiddag().getVak().getVakNaam();
        String doAvo = week.getDonderdag().getAvond().getVak().getVakNaam();
        String vrOcht = week.getVrijdag().getOchtend().getVak().getVakNaam();
        String vrMid = week.getVrijdag().getMiddag().getVak().getVakNaam();
        String vrAvo = week.getVrijdag().getAvond().getVak().getVakNaam();
        List<String> list = Arrays.asList(maOcht, maMid, maAvo, diOcht, diMid, diAvo, woOcht, woMid, woAvo, doOcht, doMid, doAvo, vrOcht, vrMid, vrAvo);
        return list;
    }

    @RequestMapping(value = "/roosteraar/docenten-koppelen-gekozen-cohort/{week}", method = RequestMethod.GET)
    public long[] docentAlOpgeslagen(@PathVariable Week week) {
        long maOcht = week.getMaandag().getOchtend().getDocent().getId();
        long maMid = week.getMaandag().getMiddag().getDocent().getId();
        long maAvo = week.getMaandag().getAvond().getDocent().getId();
        long diOcht = week.getDinsdag().getOchtend().getDocent().getId();
        long diMid = week.getDinsdag().getMiddag().getDocent().getId();
        long diAvo = week.getDinsdag().getAvond().getDocent().getId();
        long woOcht = week.getWoensdag().getOchtend().getDocent().getId();
        long woMid = week.getWoensdag().getMiddag().getDocent().getId();
        long woAvo = week.getWoensdag().getAvond().getDocent().getId();
        long doOcht = week.getDonderdag().getOchtend().getDocent().getId();
        long doMid = week.getDonderdag().getMiddag().getDocent().getId();
        long doAvo = week.getDonderdag().getAvond().getDocent().getId();
        long vrOcht = week.getVrijdag().getOchtend().getDocent().getId();
        long vrMid = week.getVrijdag().getMiddag().getDocent().getId();
        long vrAvo = week.getVrijdag().getAvond().getDocent().getId();
        long[] list = {maOcht, maMid, maAvo, diOcht, diMid, diAvo, woOcht, woMid, woAvo, doOcht, doMid, doAvo, vrOcht, vrMid, vrAvo};
        return list;
    }

    @RequestMapping(value = "/roosteraar/docenten-koppelen-gekozen-cohort/{vaknaam}/{dagdeelnaam}", method = RequestMethod.GET)
    public String[] docentenOphalen(@PathVariable String vaknaam, @PathVariable String dagdeelnaam) {
        Vak vak = vakRepository.findByVakNaam(vaknaam);
        List<User> docenten = haalDocentenJuistDagdeel(dagdeelnaam);
        String[] docentnamenMetVoorkeur = new String[docenten.size()];

        for (int i = 0; i < docenten.size(); i++) {
            docentnamenMetVoorkeur[i] = voorkeurToevoegen(docenten.get(i), vak);
        }
        return docentnamenMetVoorkeur;
    }

    private String voorkeurToevoegen(User docent, Vak vak) {
        String naamEnVoorkeur = "";
        Voorkeur voorkeur = null;
        voorkeur = voorkeurenRepository.findVoorkeurByVakAndUser(vak, docent);
        if (voorkeur != null) {
            if (voorkeur.getVoorkeurGebruiker() > 0 && voorkeur.getVoorkeurGebruiker() < 4) {
                naamEnVoorkeur = docent.getFullName() + ": " + voorkeur.getVoorkeurGebruiker();
            }
        } else {
            naamEnVoorkeur = docent.getFullName();
        }
        return naamEnVoorkeur;
    }

    private List<User> haalDocentenJuistDagdeel(String dagdeelnaam) {
        String[] dagDelen = {"docMaO", "docMaM", "docMaA", "docDiO", "docDiM", "docDiA", "docWoO", "docWoM", "docWoA",
                "docDoO", "docDoM", "docDoA", "docVrO", "docVrM", "docVrA"};
        List<User> docenten = null;

        for (int i = 0; i < dagDelen.length; i++) {
            if (dagDelen[i].equals(dagdeelnaam)) {
                String dagnaam = getDagnaam(dagdeelnaam.substring(3,5));
                if (dagdeelnaam.substring(5).equals("O")) {
                    docenten = getDocentenInOchtend(dagnaam);
                } else if (dagdeelnaam.substring(5).equals("M")) {
                    docenten = getDocentenInMiddag(dagnaam);
                } else if (dagdeelnaam.substring(5).equals("A")) {
                    docenten = getDocentenInAvond(dagnaam);
                }
                break;
            }
        }
        return docenten;
    }

    private String getDagnaam(String string) {
        String[] dagnaamKort = {"Ma", "Di", "Wo", "Do", "Vr"};
        String[] dagnaamLang = {"maandag", "dinsdag", "woensdag", "donderdag", "vrijdag"};
        String dagnaam = "";

        for (int i = 0; i < dagnaamKort.length; i++) {
            if (string.equals(dagnaamKort[i])){
                dagnaam = dagnaamLang[i];
            }
        }
        return dagnaam;
    }
}
