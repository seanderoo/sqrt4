package sqrt4.mijninzet.model;

import javax.persistence.*;

@Entity
public class Sollicitatie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn
    private User docent;

    @ManyToOne
    @JoinColumn
    private Vacature vacature;

    public Sollicitatie(User docent, Vacature vacature) {
        this.docent = docent;
        this.vacature = vacature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getDocent() {
        return docent;
    }

    public void setDocent(User docent) {
        this.docent = docent;
    }

    public Vacature getVacature() {
        return vacature;
    }

    public void setVacature(Vacature vacature) {
        this.vacature = vacature;
    }

    @Override
    public String toString() {
        return "Sollicitatie{" +
                "id=" + id +
                ", docent=" + docent +
                ", vacature=" + vacature +
                '}';
    }
}
