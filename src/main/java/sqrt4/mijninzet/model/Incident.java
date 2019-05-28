package sqrt4.mijninzet.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long incidentId;

    @ManyToOne
    @JoinColumn
    private User user;

    private LocalDate datum;
    private boolean ochtend;
    private boolean middag;
    private boolean avond;

    public Incident(LocalDate datum, boolean ochtend, boolean middag, boolean avond) {
        this.datum = datum;
        this.ochtend = ochtend;
        this.middag = middag;
        this.avond = avond;
    }

    @Override
    public String toString() {
        return "Incident{" +
                "incidentId=" + incidentId +
                ", user=" + user +
                ", datum=" + datum +
                ", ochtend=" + ochtend +
                ", middag=" + middag +
                ", avond=" + avond +
                '}';
    }

    public void setUser(User user) {
        this.user = user;
    }
}
