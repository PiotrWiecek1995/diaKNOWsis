package pl.coderslab.DiaKNOWsis.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Wyniki {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pacjent_id")
    private Pacjent pacjent;

    private LocalDate dataBadania;
    private Double morfologia;
    private Double leukocyty;
    private Double erytrocyty;
    private Double hemoglobina;
    private Double hematokryt;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pacjent getPacjent() {
        return pacjent;
    }

    public void setPacjent(Pacjent pacjent) {
        this.pacjent = pacjent;
    }

    public LocalDate getDataBadania() {
        return dataBadania;
    }

    public void setDataBadania(LocalDate dataBadania) {
        this.dataBadania = dataBadania;
    }

    public Double getMorfologia() {
        return morfologia;
    }

    public void setMorfologia(Double morfologia) {
        this.morfologia = morfologia;
    }

    public Double getLeukocyty() {
        return leukocyty;
    }

    public void setLeukocyty(Double leukocyty) {
        this.leukocyty = leukocyty;
    }

    public Double getErytrocyty() {
        return erytrocyty;
    }

    public void setErytrocyty(Double erytrocyty) {
        this.erytrocyty = erytrocyty;
    }

    public Double getHemoglobina() {
        return hemoglobina;
    }

    public void setHemoglobina(Double hemoglobina) {
        this.hemoglobina = hemoglobina;
    }

    public Double getHematokryt() {
        return hematokryt;
    }

    public void setHematokryt(Double hematokryt) {
        this.hematokryt = hematokryt;
    }
}
