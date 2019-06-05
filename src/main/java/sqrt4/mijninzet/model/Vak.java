package sqrt4.mijninzet.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Vak {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vakId")
    private int vakId;

    @Column(name = "vakNaam")
    private String vakNaam;

    @Column(name = "aantalUren")
    private int aantalUren;

    @OneToMany(mappedBy = "vak",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Vakdagdeel> vakdagdelen;

    public void setVakdagdelen(List<Vakdagdeel> vakdagdelen) {
        this.vakdagdelen = vakdagdelen;
    }

    public Vak(String vakNaam, int aantalUren) {
        this.vakNaam = vakNaam;
        this.aantalUren = aantalUren;
        this.vakdagdelen = new ArrayList();
    }

    public Vak() { super(); }

    public int getVakId() {
        return vakId;
    }

    public void setVakId(int vakId) {
        this.vakId = vakId;
    }

    public String getVakNaam() {
        return vakNaam;
    }

    public void setVakNaam(String vakNaam) {
        this.vakNaam = vakNaam;
    }

    public int getAantalUren() {
        return aantalUren;
    }

    public void setAantalUren(int aantalUren) {
        this.aantalUren = aantalUren;
    }

    public List<Vakdagdeel> getVakdagdelen(){
        return vakdagdelen;
    }

    @Override
    public String toString() {
        return "Vak{" +
                "vakId=" + vakId +
                ", vakNaam='" + vakNaam + '\'' +
                ", aantalUren=" + aantalUren +
                '}';
    }

    public List<Vakdagdeel> aantalDagdelenBerekenen(){
        List<Vakdagdeel> vakDagdelen= new ArrayList();
        final int DAGDEELUREN = 4;
        int aantalUren = this.aantalUren;
//        System.out.println("Het aantal uren is: "+aantalUren);
        int aantalDagdelen = aantalUren / DAGDEELUREN;
        if ((aantalUren % DAGDEELUREN) > 0){
            aantalDagdelen++;
        }
        for (int i = 1; i <= aantalDagdelen; i++) {
            Vakdagdeel vakdagdeelX = new Vakdagdeel(i, this);
//            System.out.println("DagdeelX is: "+vakdagdeelX);
            vakDagdelen.add(vakdagdeelX);
        }
        return vakDagdelen;

    }


}





