package sqrt4.mijninzet.model;

import javax.persistence.*;

@Entity
public class Sollicitatie implements Comparable<Sollicitatie> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sollicitatieId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Vacature vacature;

    private Status status;

    @Override
    public int compareTo(Sollicitatie o) {
        return this.vacature.getVacatureNaam().compareTo(o.vacature.getVacatureNaam());
    }

    public enum Status {
        IN_BEHANDELING,
        TOEGEWEZEN,
        AFGEWEZEN
    }

    public Sollicitatie(User user, Vacature vacature) {
        this.user = user;
        this.vacature = vacature;
        this.status = Status.IN_BEHANDELING;
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

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getStatus() {
        switch (this.status) {
            case IN_BEHANDELING:
                return "In behandeling";
            case TOEGEWEZEN:
                return "Toegewezen";
            case AFGEWEZEN:
                return "Afgewezen";
        }
        return null;
    }


    @Override
    public String toString() {
        return "Sollicitatie{" +
                "sollicitatieId=" + sollicitatieId +
                ", user=" + user +
                ", vacature=" + vacature +
                '}' + status;
    }
}
