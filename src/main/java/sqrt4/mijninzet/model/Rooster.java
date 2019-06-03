package sqrt4.mijninzet.model;

import sqrt4.mijninzet.model.Beschikbaarheid.Cohort;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Rooster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roosterId;

    @OneToOne
    @JoinColumn
    private Cohort cohort;

    @OneToMany
    private List<Vak> vakken;

    @OneToMany
    private List<User> docenten;

    public Rooster(Cohort cohort, List<Vak> vakken, List<User> docenten) {
        this.cohort = cohort;
        this.vakken = vakken;
        this.docenten = docenten;
    }

    public Rooster(Cohort cohort) {
        this.cohort = cohort;
        this.vakken = new ArrayList<>();
        this.docenten = new ArrayList<>();
    }

    public int getRoosterId() {
        return roosterId;
    }

    public void setRoosterId(int roosterId) {
        this.roosterId = roosterId;
    }

    public Cohort getCohort() {
        return cohort;
    }

    public void setCohort(Cohort cohort) {
        this.cohort = cohort;
    }

    public List<Vak> getVakken() {
        return vakken;
    }

    public void setVakken(List<Vak> vakken) {
        this.vakken = vakken;
    }

    public List<User> getDocenten() {
        return docenten;
    }

    public void setDocenten(List<User> docenten) {
        this.docenten = docenten;
    }
}
