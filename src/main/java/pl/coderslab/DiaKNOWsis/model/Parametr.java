package pl.coderslab.DiaKNOWsis.model;

import javax.persistence.*;

@Entity
public class Parametr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nazwa;

    private Double minWartosc;
    private Double maxWartosc;

    @ManyToOne
    @JoinColumn(name = "choroba_id")
    private Choroba choroba;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Double getMinWartosc() {
        return minWartosc;
    }

    public void setMinWartosc(Double minWartosc) {
        this.minWartosc = minWartosc;
    }

    public Double getMaxWartosc() {
        return maxWartosc;
    }

    public void setMaxWartosc(Double maxWartosc) {
        this.maxWartosc = maxWartosc;
    }

    public Choroba getChoroba() {
        return choroba;
    }

    public void setChoroba(Choroba choroba) {
        this.choroba = choroba;
    }
}
