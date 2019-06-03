package sqrt4.mijninzet.model.Beschikbaarheid;

import sqrt4.mijninzet.model.User;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cohort {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String cohortNaam;
    private int startWeek;
    private int startJaar;
    private int eindWeek;

    @OneToMany(mappedBy = "cohort", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("jaarNummer asc, weekNummer asc")
    private List<Week> weekList;

//    @ManyToOne
//    @JoinColumn
//    private User user;


    public Cohort() {

        weekList = new ArrayList<>();

        for (int i = 0; i < numberOfWeeks(startWeek, eindWeek); i++) {
            Week week = new Week(startWeek + i, startJaar);
            weekList.add(week);
            week.setCohort(this);
            if (week.getWeekNummer() > wekenInJaar(startJaar)) {
                week.setWeekNummer(week.getWeekNummer() - wekenInJaar(startJaar));
                week.setJaarNummer(startJaar + 1);
                week.pasWeekNummerDagenAan(week.getWeekNummer());
            }
        }

    }

    public Cohort(int cohortNaam, int startWeek, int startJaar, int eindWeek) {
        this.cohortNaam = setCohortrName(cohortNaam);
        this.startWeek = startWeek;
        this.startJaar = startJaar;
        this.eindWeek = eindWeek;
        weekList = new ArrayList<>();

        for (int i = 0; i < numberOfWeeks(startWeek, eindWeek); i++) {
            Week week = new Week(startWeek + i, startJaar);
            weekList.add(week);
            week.setCohort(this);
            if (week.getWeekNummer() > wekenInJaar(startJaar)) {
                week.setWeekNummer(week.getWeekNummer() - wekenInJaar(startJaar));
                week.setJaarNummer(startJaar + 1);
                week.pasWeekNummerDagenAan(week.getWeekNummer());
            }
        }
    }
    // Wellicht heroverwegen om cohortnaam vrij in te vullen, dit kan in de toekomst worden gebruikt voor elke
    // periode die er is, namelijk cohorten of langere/ kortere periodes. Voorbeeld: periode wk 40-2019 tot en met wk 6-2020
    public String setCohortrName(int cohortNummer) {
        StringBuilder sb = new StringBuilder();
        String appendix = "Cohort";

        return sb.append(appendix).append(" ").append(cohortNummer).toString();
    }

    public int numberOfWeeks(int startWeek, int eindWeek){
        int aantalWeken= 0;
        if(eindWeek>startWeek){
            aantalWeken =(eindWeek - startWeek)+1;
        } else{
            aantalWeken = ((wekenInJaar(startJaar)-startWeek)+1)+eindWeek;
        }
        return aantalWeken;
    }

    public int wekenInJaar(int startJaar) {
        final int REG_JAAR = 52;
        final int SPEC_JAAR = 53;
        int aantalWeken = 0;
        int [] SPECIAAL_JAREN = {2015,2020,2026,2032,2037,2043,2048,2054};
        for (int jaar : SPECIAAL_JAREN) {
            if (startJaar == jaar ) {
                aantalWeken = SPEC_JAAR;
                break;
            } else {
                aantalWeken = REG_JAAR;
            }

        }
        return aantalWeken;
    }

    @Override
    public String toString() {
        return "Cohort{\n" +
                "cohortNaam='" + cohortNaam + "\'" +
                ",\n startWeek=" + startWeek +
                ",\n startJaar=" + startJaar +
                ",\n eindJaar=" + weekList.get(weekList.size() - 1).getJaarNummer() +
                ",\n eindWeek=" + eindWeek +
//                ",\n weekList=" + weekList +
                "}";
    }

    public String getCohortNaam() {
        return cohortNaam;
    }

    public void beschikbaarheidAanpassen(Week nieuweWeek) {
        String[] weekdagen = {"maandag", "dinsdag", "woensdag", "donderdag", "vrijdag"};
        for (Week week : weekList) {
            for (int i = 0; i < weekdagen.length; i++) {
                week.getDag(weekdagen[i]).setOchtend(nieuweWeek.getDag(weekdagen[i]).getOchtend());
                week.getDag(weekdagen[i]).setMiddag(nieuweWeek.getDag(weekdagen[i]).getMiddag());
                week.getDag(weekdagen[i]).setAvond(nieuweWeek.getDag(weekdagen[i]).getAvond());
            }
        }
    }

//    public void setUser(User user) {
//        this.user = user;
//    }

    public Week getFirstWeek() {
        return weekList.get(0);
    }

    public int getId() {
        return id;
    }

//    public User getUser() {
//        return user;
//    }
}
