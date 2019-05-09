package sqrt4.mijninzet.model.Beschikbaarheid;


import java.util.ArrayList;


public class Week {
    private ArrayList<Dag> week = new ArrayList<Dag>();
    private int weekNummer;
    private int jaarNummer;


    public Week(int weekNummer, int jaarNummer){
        super();
        this.weekNummer = weekNummer;
        this.jaarNummer = jaarNummer;
        week.add(new Dag("maandag", weekNummer, jaarNummer));
        week.add(new Dag("dinsdag", weekNummer, jaarNummer));
        week.add(new Dag("woensdag", weekNummer, jaarNummer));
        week.add(new Dag("donderdag", weekNummer, jaarNummer));
        week.add(new Dag("vrijdag", weekNummer,jaarNummer));
    }


    @Override
    public String toString() {
        return "Week{" +
                "week=" + week +
                ", weekNummer=" + weekNummer +
                '}';
    }

    public int getWeekNummer() {
        return weekNummer;
    }

    public void setWeekNummer(int weekNummer) {
        this.weekNummer = weekNummer;
    }

    public int getJaarNummer() {
        return jaarNummer;
    }

    public void setJaarNummer(int jaarNummer) {
        this.jaarNummer = jaarNummer;
    }
}
