package sqrt4.mijninzet.model.Beschikbaarheid;


import sqrt4.mijninzet.model.User;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@Entity
public class Dag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn
    private Week week;

//    @ManyToOne
//    @JoinColumn
//    private User user;

    private String dagnaam;
    private int weekNummer;
    private int jaarNummer;
    private LocalDate datum;
    private boolean ochtend;
    private boolean middag;
    private boolean avond;

    public Dag(String dagnaam, int weekNummer, int jaarNummer) {
        this(dagnaam, weekNummer, jaarNummer, true, true, true);
        this.datum = setDatumDag(dagnaam);
    }

    public Dag(String name, int weekNummer, int jaarNummer, boolean ochtend, boolean middag, boolean avond){
        super();
        this.dagnaam = name;
        this.weekNummer = weekNummer;
        this.jaarNummer = jaarNummer;
        this.ochtend = ochtend;
        this.middag = middag;
        this.avond = avond;
        this.datum = setDatumDag(dagnaam);
    }

    public Dag() {}

    public Dag(LocalDate datum, boolean ochtend, boolean middag, boolean avond) {
        this("", 0, 0);
        this.datum = datum;
        this.ochtend = ochtend;
        this.middag = middag;
        this.avond = avond;
    }

    public LocalDate setDatumDag(String dagnaam){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, this.jaarNummer);
        cal.set(Calendar.WEEK_OF_YEAR, this.weekNummer);
        cal.set(Calendar.DAY_OF_WEEK, nlDagNaarJava(dagnaam));
        cal.getFirstDayOfWeek();

        String date = sdf.format(cal.getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }

    public int nlDagNaarJava(String dagNaam){
        int var = 0;

        switch(dagNaam){
            case "maandag":
                var = Calendar.MONDAY;
                break;
            case "dinsdag":
                var = Calendar.TUESDAY;
                break;
            case "woensdag":
                var = Calendar.WEDNESDAY;
                break;
            case "donderdag":
                var = Calendar.THURSDAY;
                break;
            case "vrijdag":
                var = Calendar.FRIDAY;
                break;
        }
        return var;
    }

    public int getBooleanAsInt(boolean value) {
        return value ? 1 : 0;
    }

    public void setOchtend(boolean ochtend) {
        this.ochtend = ochtend;
    }

    public void setMiddag(boolean middag) {
        this.middag = middag;
    }

    public void setAvond(boolean avond) {
        this.avond = avond;
    }

    public boolean getOchtend() {
        return ochtend;
    }

    public int getOchtendAsInt() {
        return ochtend ? 1 : 0;
    }

    public int getMiddagAsINt() {
        return middag ? 1 : 0;
    }

    public int getAvondAsInt() {
        return avond ? 1 : 0;
    }

    public boolean getMiddag() {
        return middag;
    }

    public boolean getAvond() {
        return avond;
    }

    public String getDagnaam() {
        return dagnaam;
    }

    public LocalDate getDatum() {
        return datum;
    }

    @Override
    public String toString() {
        return "Dag{" +
                "dagnaam='" + dagnaam + '\'' +
                ", weekNummer=" + weekNummer +
                ", jaarNummer=" + jaarNummer +
                ", datum=" + datum +
                ", ochtend=" + ochtend +
                ", middag=" + middag +
                ", avond=" + avond +
                '}';
    }

    public int getWeekNummer() {
        return weekNummer;
    }

    public int getJaarNummer() {
        return jaarNummer;
    }

    public int getId() {
        return id;
    }

    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    public void setWeekNummer(int weekNummer) {
        this.weekNummer = weekNummer;
    }

    public boolean isOchtend() {
        return ochtend;
    }

    public boolean isMiddag() {
        return middag;
    }

    public boolean isAvond() {
        return avond;
    }
}
