package sqrt4.mijninzet.model;

import javax.persistence.*;

@Entity
public class Voorkeur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int voorkeurId;

    @OneToOne
    private User user;

    @OneToOne
    private Vak vak;

    private int voorkeurGebruiker;

    public Voorkeur(int voorkeurId, User user, Vak vak, int voorkeurGebruiker) {
        this.voorkeurId = voorkeurId;
        this.user = user;
        this.vak = vak;
        this.voorkeurGebruiker = voorkeurGebruiker;
    }

    public Voorkeur() { super();   }

    public int getVoorkeurId() {
        return voorkeurId;
    }

    public void setVoorkeurId(int voorkeurId) {
        this.voorkeurId = voorkeurId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vak getVak() {
        return vak;
    }

    public void setVak(Vak vak) {
        this.vak = vak;
    }

    public int getVoorkeurGebruiker() {
        return voorkeurGebruiker;
    }

    public void setVoorkeurGebruiker(int voorkeurGebruiker) {
        this.voorkeurGebruiker = voorkeurGebruiker;
    }

    @Override
    public String toString() {
        return "Voorkeur{" +
                "voorkeurId=" + voorkeurId +
                ", user=" + user +
                ", vak=" + vak +
                ", voorkeur='" + voorkeurGebruiker + '\'' +
                '}';
    }
}
