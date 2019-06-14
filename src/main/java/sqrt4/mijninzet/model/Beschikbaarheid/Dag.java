package sqrt4.mijninzet.model.Beschikbaarheid;

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
    private Week week;

    private String dagnaam;
    private int weekNummer;
    private int jaarNummer;
    private LocalDate datum;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Dagdeel ochtend;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Dagdeel middag;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Dagdeel avond;

    public Dag(String dagnaam, int weekNummer, int jaarNummer) {
        this(dagnaam, weekNummer, jaarNummer, true, true, true);
        this.datum = setDatumDag(dagnaam);
    }

    public Dag(String name, int weekNummer, int jaarNummer, boolean ochtend, boolean middag, boolean avond){
        super();
        this.dagnaam = name;
        this.weekNummer = weekNummer;
        this.jaarNummer = jaarNummer;

        this.ochtend = new Dagdeel(ochtend,this);
        this.middag = new Dagdeel(middag,this);
        this.avond = new Dagdeel(avond,this);

        this.datum = setDatumDag(dagnaam);
    }

    public Dag() {}

    private LocalDate setDatumDag(String dagnaam){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, this.jaarNummer);
        cal.set(Calendar.WEEK_OF_YEAR, this.weekNummer);
        cal.set(Calendar.DAY_OF_WEEK, nederlandsDagNaarJava(dagnaam));
        cal.getFirstDayOfWeek();

        String date = sdf.format(cal.getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }

    public int nederlandsDagNaarJava(String dagNaam){
        int dayNameJava = 0;

        switch(dagNaam){
            case "maandag":
                dayNameJava = Calendar.MONDAY;
                break;
            case "dinsdag":
                dayNameJava = Calendar.TUESDAY;
                break;
            case "woensdag":
                dayNameJava = Calendar.WEDNESDAY;
                break;
            case "donderdag":
                dayNameJava = Calendar.THURSDAY;
                break;
            case "vrijdag":
                dayNameJava = Calendar.FRIDAY;
                break;
        }
        return dayNameJava;
    }

    public Dagdeel getOchtend() {
        return ochtend;
    }

    public Dagdeel getMiddag() {
        return middag;
    }

    public Dagdeel getAvond() {
        return avond;
    }

    public String getDagnaam() {
        return dagnaam;
    }

    public String getDatum() {
        LocalDate date = datum;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String formattedString = date.format(formatter);
        return formattedString;
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

    public void setOchtend(Dagdeel ochtend) {
        this.ochtend = ochtend;
    }

    public void setMiddag(Dagdeel middag) {
        this.middag = middag;
    }

    public void setAvond(Dagdeel avond) {
        this.avond = avond;
    }
}
