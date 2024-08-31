package pl.coderslab.DiaKNOWsis.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Choroba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nazwa;

    @OneToMany(mappedBy = "choroba", cascade = CascadeType.ALL)
    private Set<Parametr> parametry;


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

    public Set<Parametr> getParametry() {
        return parametry;
    }

    public void setParametry(Set<Parametr> parametry) {
        this.parametry = parametry;
    }
}
