package sqrt4.mijninzet.model;

import sqrt4.mijninzet.model.users.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Vacature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String vacatureNaam;
    @Column(name = "omschrijving")
    private String omschrijving;
    private int aantalUren;
    private String eisen;
    @ManyToMany
    @JoinTable
    private Collection<User> docenten;

    public Vacature(String vacatureNaam, String omschrijving, int aantalUren, String eisen) {
        this.vacatureNaam = vacatureNaam;
        this.omschrijving = omschrijving;
        this.aantalUren = aantalUren;
        this.eisen = eisen;
        docenten = new ArrayList<>();
    }
    public Vacature() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVacatureNaam() {
        return vacatureNaam;
    }

    public void setVacatureNaam(String vacatureNaam) {
        this.vacatureNaam = vacatureNaam;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public int getAantalUren() {
        return aantalUren;
    }

    public void setAantalUren(int aantalUren) {
        this.aantalUren = aantalUren;
    }

    public String getEisen() {
        return eisen;
    }

    public void setEisen(String eisen) {
        this.eisen = eisen;
    }

    public Collection<User> getDocenten() {
        return docenten;
    }

    public void setDocenten(Collection<User> docenten) {
        this.docenten = docenten;
    }
}
