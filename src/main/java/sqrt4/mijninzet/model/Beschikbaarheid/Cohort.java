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
    private List<Week> cohortList;

    @ManyToOne
    private User user;


    public Cohort() {
        genereerWeken();
    }

    public Cohort(int cohortNaam, int startWeek, int startJaar, int eindWeek) {
        this.cohortNaam = setCohortName(cohortNaam);
        this.startWeek = startWeek;
        this.startJaar = startJaar;
        this.eindWeek = eindWeek;

        genereerWeken();
    }

    public String setCohortName(int cohortNummer) {
        StringBuilder sb = new StringBuilder();
        String appendix = "Cohort";

        return sb.append(appendix).append(" ").append(cohortNummer).toString();
    }

    private int aantalWekenInCohort(int startWeek, int eindWeek) {
        int aantalWeken = 0;
        if (eindWeek > startWeek) {
            aantalWeken = (eindWeek - startWeek) + 1;
        } else {
            aantalWeken = ((hoeveelWekenInJaar(startJaar) - startWeek) + 1) + eindWeek;
        }
        return aantalWeken;
    }

    private int hoeveelWekenInJaar(int startJaar) {
        final int REGULIER_JAAR = 52;
        final int SPECIAAL_JAAR = 53;
        int aantalWeken = 0;
        int[] SPECIALE_JAREN = {2015, 2020, 2026, 2032, 2037, 2043, 2048, 2054};
        for (int jaar : SPECIALE_JAREN) {
            if (startJaar == jaar) {
                aantalWeken = SPECIAAL_JAAR;
                break;
            } else {
                aantalWeken = REGULIER_JAAR;
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
                ",\n eindJaar=" + cohortList.get(cohortList.size() - 1).getJaarNummer() +
                ",\n eindWeek=" + eindWeek +
                ",\n cohortList=" + cohortList +
                "}";
    }

    public String getCohortNaam() {
        return cohortNaam;
    }

    public void beschikbaarheidAanpassen(Week nieuweWeek) {
        String[] weekdagen = {"maandag", "dinsdag", "woensdag", "donderdag", "vrijdag"};
        for (Week week : cohortList) {
            for (int i = 0; i < weekdagen.length; i++) {
                week.getDag(weekdagen[i]).setOchtend(nieuweWeek.getDag(weekdagen[i]).getOchtend());
                week.getDag(weekdagen[i]).setMiddag(nieuweWeek.getDag(weekdagen[i]).getMiddag());
                week.getDag(weekdagen[i]).setAvond(nieuweWeek.getDag(weekdagen[i]).getAvond());
            }
        }
    }

    private void genereerWeken() {
        cohortList = new ArrayList<>();

        for (int i = 0; i < aantalWekenInCohort(startWeek, eindWeek); i++) {
            Week week = new Week(startWeek + i, startJaar);
            cohortList.add(week);
            week.setCohort(this);

            int weeknr = week.getWeekNummer();
            int aantalWekenInJaar = hoeveelWekenInJaar(startJaar);

            if (weeknr > aantalWekenInJaar) {
                week.setWeekNummer(weeknr - aantalWekenInJaar);
                week.setJaarNummer(startJaar + 1);
                week.pasWeekNummerDagenAan(weeknr);
            }
        }
    }

    public int getEindJaar() {
        int lastWeekInList = (cohortList.size() - 1);
        return cohortList.get(lastWeekInList).getJaarNummer();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public int getStartJaar() {
        return startJaar;
    }

    public int getEindWeek() {
        return eindWeek;
    }

    public List<Week> getCohortList() {
        return cohortList;
    }
}

