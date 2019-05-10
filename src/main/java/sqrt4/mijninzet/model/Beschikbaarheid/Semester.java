package sqrt4.mijninzet.model.Beschikbaarheid;

import java.util.ArrayList;

public class Semester {
    private final int REG_JAAR = 52;
    private final int HALF_JAAR = 26;
    private final int SPEC_JAAR = 53;
    private final int [] SPECIAAL_JAREN = {2015,2020,2026,2032,2037,2043,2048,2054};
    private String semesterNaam;
    private int startWeek;
    private int startJaar;
    private int eindWeek;
    private ArrayList<Week> semesterList = new ArrayList<Week>();

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
}
