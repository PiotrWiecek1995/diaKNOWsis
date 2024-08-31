package pl.coderslab.DiaKNOWsis.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class Pacjent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Imię jest wymagane")
    private String imie;

    @NotEmpty(message = "Nazwisko jest wymagane")
    private String nazwisko;



    @Pattern(regexp = "\\d{11}", message = "PESEL musi zawierać 11 cyfr")
    private String pesel;

    @NotEmpty(message = "Płeć jest wymagana")
    private String plec;

    @NotEmpty(message = "Adres jest wymagany")
    private String adres;

    @Pattern(regexp = "\\d{9}", message = "Numer telefonu musi zawierać 9 cyfr")
    private String telefon;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }



    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
