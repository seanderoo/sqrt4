package sqrt4.mijninzet.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Incident {
    @Id
    private LocalDate datum;

    @ManyToOne
    @JoinColumn
    private User user;


    private boolean ochtend;
    private boolean middag;
    private boolean avond;

    public Incident() {

    }

    public Incident(LocalDate datum, boolean ochtend, boolean middag, boolean avond) {
        this.datum = datum;
        this.ochtend = ochtend;
        this.middag = middag;
        this.avond = avond;
    }

    @Override
    public String toString() {
        return "Incident{" +
                ", user=" + user +
                ", datum=" + datum +
                ", ochtend=" + ochtend +
                ", middag=" + middag +
                ", avond=" + avond +
                '}';
    }



    public User getUser() {
        return user;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public boolean isOchtend() {
        return ochtend;
    }

    public void setOchtend(boolean ochtend) {
        this.ochtend = ochtend;
    }

    public boolean isMiddag() {
        return middag;
    }

    public void setMiddag(boolean middag) {
        this.middag = middag;
    }

    public boolean isAvond() {
        return avond;
    }

    public void setAvond(boolean avond) {
        this.avond = avond;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

