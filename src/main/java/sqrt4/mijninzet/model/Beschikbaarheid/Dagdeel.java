package sqrt4.mijninzet.model.Beschikbaarheid;

import com.fasterxml.jackson.annotation.JsonIgnore;
import sqrt4.mijninzet.model.User;
import sqrt4.mijninzet.model.Vak;

import javax.persistence.*;

@Entity
public class Dagdeel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean beschikbaar;

    @OneToOne
    private Vak vak;

    @OneToOne
    private User docent;

    @OneToOne
    @JsonIgnore
    private Dag dag;


    public Dagdeel(){}

    public Dagdeel(boolean beschikbaar, Dag dag) {
        this.beschikbaar = beschikbaar;
        this.dag = dag;
    }

    public Dagdeel(boolean beschikbaar, Vak vak, User docent, Dag dag) {
        this.beschikbaar = beschikbaar;
        this.vak = vak;
        this.docent = docent;
        this.dag = dag;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getBeschikbaar() {
        return beschikbaar;
    }

    public void setBeschikbaar(boolean beschikbaar) {
        this.beschikbaar = beschikbaar;
    }

    public Vak getVak() {
        if (vak == null) {
            vak = new Vak("Geen les", 0);
        }
        return vak;
    }

    public void setVak(Vak vak) {
        this.vak = vak;
    }

    public User getDocent() {
        if ( docent == null ) {
            docent = new User("G", "G123", "DOCENT", "", "Geen", "docent", "");
        }
        return docent;
    }

    public void setDocent(User docent) {
        this.docent = docent;
    }

    public Dag getDag() {
        return dag;
    }

    public void setDag(Dag dag) {
        this.dag = dag;
    }


}
