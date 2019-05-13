package sqrt4.mijninzet.model.Beschikbaarheid;

import sqrt4.mijninzet.model.users.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.ArrayList;

@Entity
public class Semester {
    private final int REG_JAAR = 52;
    private final int HALF_JAAR = 26;
    private final int SPEC_JAAR = 53;
    private final int [] SPECIAAL_JAREN = {2015,2020,2026,2032,2037,2043,2048,2054};

    @Id
    private String semesterNaam;
    private int startWeek;
    private int startJaar;
    private int eindWeek;
    private ArrayList<Week> semesterList = new ArrayList<Week>();

    @ManyToOne
    private User user;

    //Deze heb ik gemaakt om in de AlgemeneBeschikbaarheiddsController te werken. Is dat wel nodig?
    public Semester() {
        this(2,1970,27);
    }

    public Semester(int startWeek, int startJaar, int eindWeek) {
        this.semesterNaam = setSemesterName(startWeek, startJaar);
        this.startWeek = startWeek;
        this.startJaar = startJaar;
        this.eindWeek = eindWeek;
        for (int i = 0; i < numberOfWeeks(startWeek, eindWeek); i++) {
            semesterList.add(new Week(startWeek + i, startJaar));
            if (semesterList.get(i).getWeekNummer() > wekenInJaar(startJaar)) {
                semesterList.get(i).setWeekNummer(semesterList.get(i).getWeekNummer() - wekenInJaar(startJaar));
                semesterList.get(i).setJaarNummer(startJaar + 1);
            }
        }
    }
    // Wellicht heroverwegen om semesternaam vrij in te vullen, dit kan in de toekomst worden gebruikt voor elke
    // periode die er is, namelijk cohorten of langere/ kortere periodes. Voorbeeld: periode wk 40-2019 tot en met wk 6-2020
    public String setSemesterName(int startWeek, int startJaar){
        StringBuilder sb = new StringBuilder();
        String appendix = "";
        if(startWeek>HALF_JAAR){
            appendix = "II";
        } else {
            appendix = "I";
        }
        return sb.append(startJaar).append("-").append(appendix).toString();
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
        int aantalWeken = 0;
        for (int jaar : SPECIAAL_JAREN) {
            if ( startJaar == jaar ) {
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
        return "Semester{\n" +
                "semesterNaam='" + semesterNaam + "\'" +
                ",\n startWeek=" + startWeek +
                ",\n startJaar=" + startJaar +
                ",\n eindJaar=" + semesterList.get(semesterList.size()-1).getJaarNummer() +
                ",\n eindWeek=" + eindWeek +
                ",\n semesterList=" + semesterList +
                "}";
    }

    public String getSemesterNaam() {
        return semesterNaam;
    }

    public void beschikbaarheidAanpassen(Week nieuweWeek) {
        String[] weekdagen = {"maandag", "dinsdag", "woensdag", "donderdag", "vrijdag"};
        for (Week week : semesterList) {
            for (int i = 0; i < weekdagen.length; i++) {
                week.getDag(weekdagen[i]).setOchtend(nieuweWeek.getDag(weekdagen[i]).getOchtend());
                week.getDag(weekdagen[i]).setMiddag(nieuweWeek.getDag(weekdagen[i]).getMiddag());
                week.getDag(weekdagen[i]).setAvond(nieuweWeek.getDag(weekdagen[i]).getAvond());
            }
        }
    }

}
