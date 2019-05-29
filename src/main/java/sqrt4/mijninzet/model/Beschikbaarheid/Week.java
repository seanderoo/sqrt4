package sqrt4.mijninzet.model.Beschikbaarheid;


import sqrt4.mijninzet.model.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Week {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Dag> dagenlijst;

    private int weekNummer;
    private int jaarNummer;

    @OneToOne
    User user;

    @ManyToOne
    private Semester semester;


    public Week(int weekNummer, int jaarNummer){
        super();
        this.weekNummer = weekNummer;
        this.jaarNummer = jaarNummer;
        voegDagenToe();
    }

    public Week() {
        this(0,0);
        voegDagenToe();
    }

    private void voegDagenToe() {
        dagenlijst = new ArrayList<>();
        dagenlijst.add(new Dag("maandag", weekNummer, jaarNummer));
        dagenlijst.add(new Dag("dinsdag", weekNummer, jaarNummer));
        dagenlijst.add(new Dag("woensdag", weekNummer, jaarNummer));
        dagenlijst.add(new Dag("donderdag", weekNummer, jaarNummer));
        dagenlijst.add(new Dag("vrijdag", weekNummer,jaarNummer));
        setWeekVoorDagen();
    }


    @Override
    public String toString() {
        return "Week " + weekNummer + "\n" +
                "User: " + user + "\n" +
                "dagenlijst=" + dagenlijst + "\n";
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

    public Dag getDag(String dagnaam) {
        Dag dag = null;

        for (Dag d : dagenlijst) {
            if (d.getDagnaam().equals(dagnaam)) {
                dag = d;
            }
        }
        return dag;
    }

    public int getDaysInTheWeek() {

        int teller = 0;
        for (Object o: dagenlijst) {
            if(o instanceof Dag){
                teller++;
        }
        }
        return teller;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    private void setWeekVoorDagen() {
        for (Dag dag : dagenlijst) {
            dag.setWeek(this);
        }
    }

    public void pasWeekNummerDagenAan(int nieuwNummer) {
        for (Dag dag : dagenlijst) {
            dag.setWeekNummer(nieuwNummer);
        }
    }


    public Dag getMaandag() {
        Dag dag = null;
        for (Dag d : dagenlijst) {
            if (d.getDagnaam().equals("maandag")) {
                dag = d;
            }
        }
        return dag;
    }
    public Dag getDinsdag() {
        Dag dag = null;
        for (Dag d : dagenlijst) {
            if (d.getDagnaam().equals("dinsdag")) {
                dag = d;
            }
        }
        return dag;
    }
    public Dag getWoensdag() {
        Dag dag = null;
        for (Dag d : dagenlijst) {
            if (d.getDagnaam().equals("woensdag")) {
                dag = d;
            }
        }
        return dag;
    }
    public Dag getDonderdag() {
        Dag dag = null;
        for (Dag d : dagenlijst) {
            if (d.getDagnaam().equals("donderdag")) {
                dag = d;
            }
        }
        return dag;
    }
    public Dag getVrijdag() {
        Dag dag = null;
        for (Dag d : dagenlijst) {
            if (d.getDagnaam().equals("vrijdag")) {
                dag = d;
            }
        }
        return dag;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
