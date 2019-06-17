package sqrt4.mijninzet.model;

import javax.persistence.*;

@Entity
public class Sollicitatie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sollicitatieId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Vacature vacature;

    private String status;

    public Sollicitatie(User user, Vacature vacature) {
        this.user = user;
        this.vacature = vacature;
        this.status = "in behandeling";
    }

    public Sollicitatie() {
    }

    public int getSollicitatieId() {
        return sollicitatieId;
    }

    public void setSollicitatieId(int sollicitatieId) {
        this.sollicitatieId = sollicitatieId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vacature getVacature() {
        return vacature;
    }

    public void setVacature(Vacature vacature) {
        this.vacature = vacature;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Sollicitatie{" +
                "sollicitatieId=" + sollicitatieId +
                ", user=" + user +
                ", vacature=" + vacature +
                '}';
    }
}
