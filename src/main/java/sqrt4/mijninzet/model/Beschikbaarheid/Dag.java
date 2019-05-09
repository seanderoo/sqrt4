package sqrt4.mijninzet.model.Beschikbaarheid;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Dag {
    private String dagnaam;
    private int weekNummer;
    private int jaarNummer;
    private LocalDate datum;
    private boolean ochtend;
    private boolean middag;
    private boolean avond;

    public Dag(String dagnaam, int weekNummer, int jaarNummer) {
        this.dagnaam = dagnaam;
        this.weekNummer = weekNummer;
        this.jaarNummer = jaarNummer;
        this.datum = setDatumDag(dagnaam);
        ochtend = true;
        middag = true;
        avond = true;
    }

    public Dag(String name, int weekNummer, int jaarNummer, boolean ochtend, boolean middag, boolean avond){
        super();
        this.weekNummer = weekNummer;
        this.jaarNummer = jaarNummer;
        this.ochtend = ochtend;
        this.middag = middag;
        this.avond = avond;
    }
    public LocalDate setDatumDag(String dagnaam){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, jaarNummer);
        cal.set(Calendar.WEEK_OF_YEAR, weekNummer);
        cal.set(Calendar.DAY_OF_WEEK, getFunction(dagnaam));
        System.out.println(sdf.format(cal.getTime()));

        String date = sdf.format(cal.getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
//        System.out.println("Parsed Date?= " + date);

    }
    public int getFunction(String dagNaam){
        int var = 0;

        switch(dagNaam){
            case "maandag":
                var = 2;
                break;
            case "dinsdag":
                var = 3;
                break;
            case "woensdag":
                var = 4;
                break;
            case "donderdag":
                var = 5;
                break;
            case "vrijdag":
                var = 6;
                break;
        }
        return var;
    }

    @Override
    public String toString() {
        return "Dag{" +
                "dagnaam='" + dagnaam + '\'' +
                ", datum=" + datum +
                '}';
    }
}
