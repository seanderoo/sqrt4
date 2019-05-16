package sqrt4.mijninzet.model.Beschikbaarheid;


import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Week {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int week_id;

    @OneToMany(mappedBy = "week", cascade = CascadeType.PERSIST)
    private List<Dag> week;

    private int weekNummer;
    private int jaarNummer;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;


    public Week(int weekNummer, int jaarNummer){
        super();
        this.weekNummer = weekNummer;
        this.jaarNummer = jaarNummer;
        week = new ArrayList<>();
        week.add(new Dag("maandag", weekNummer, jaarNummer));
        week.add(new Dag("dinsdag", weekNummer, jaarNummer));
        week.add(new Dag("woensdag", weekNummer, jaarNummer));
        week.add(new Dag("donderdag", weekNummer, jaarNummer));
        week.add(new Dag("vrijdag", weekNummer,jaarNummer));
    }

    public Week() {
        this(1,2019);
        week = new ArrayList<>();
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

    public Dag getDag(String dagnaam) {
        Dag dag = null;

        for (Dag d : week) {
            if (d.getDagnaam().equals(dagnaam)) {
                dag = d;
            }
        }

        return dag;
    }

    public int getDaysInTheWeek() {

        int teller = 0;
        for (Object o: week) {
            if(o instanceof Dag){
                teller++;
        }
        }
        return teller;
    }

    public int getId() {
        return week_id;
    }

    public void setId(int id) {
        this.week_id = id;
    }

    public List<Dag> getWeek() {
        return week;
    }

    public void setWeek(List<Dag> week) {
        this.week = week;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }
}
