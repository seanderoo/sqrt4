package sqrt4.mijninzet.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate datum;

    @ManyToOne
    @JoinColumn
    private User user;

    private String opmerkingGebruiker;

    private boolean ochtend;
    private boolean middag;
    private boolean avond;

    private String status = "in behandeling";

    private String managerToelichting = " ";

    public Incident() {

    }
    public Incident(LocalDate datum, boolean ochtend, boolean middag, boolean avond) {
        this.datum = datum;
        this.ochtend = ochtend;
        this.middag = middag;
        this.avond = avond;
    }

    public Incident(LocalDate datum, String opmerkingGebruiker, boolean ochtend, boolean middag, boolean avond) {
        this.datum = datum;
        this.opmerkingGebruiker = opmerkingGebruiker;
        this.ochtend = ochtend;
        this.middag = middag;
        this.avond = avond;
    }

    public Incident(LocalDate datum, boolean ochtend, boolean middag, boolean avond, String opmerkingGebruiker) {
        this.datum = datum;
        this.ochtend = ochtend;
        this.middag = middag;
        this.avond = avond;
        this.opmerkingGebruiker = opmerkingGebruiker;
    }

    public Incident(Long id, LocalDate datum, boolean ochtend, boolean middag, boolean avond,
                    String opmerkingGebruiker,
                    String status, String managerToelichting) {
        this.id = id;
        this.datum = datum;
        this.ochtend = ochtend;
        this.middag = middag;
        this.avond = avond;
        this.opmerkingGebruiker = opmerkingGebruiker;
        this.status = status;
        this.managerToelichting = managerToelichting;
    }

    @Override
    public String toString() {
        return "Incident{" +
                "datum=" + datum +
                ", user=" + user +
                ", opmerkingGebruiker='" + opmerkingGebruiker + '\'' +
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

    public String getOpmerkingGebruiker() {
        return opmerkingGebruiker;
    }

    public void setOpmerkingGebruiker(String opmerkingGebruiker) {
        this.opmerkingGebruiker = opmerkingGebruiker;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getManagerToelichting() {
        return managerToelichting;
    }

    public void setManagerToelichting(String managerToelichting) {
        this.managerToelichting = managerToelichting;
    }

    public Long getId() {
        return id;
    }
}

