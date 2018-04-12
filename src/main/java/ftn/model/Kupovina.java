package ftn.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Kupovina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long teatarId;

    private Long rekvizitId;

    private Long korisnikId;

    private Double cena;

    private String datum;

    public Kupovina() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeatarId() {
        return teatarId;
    }

    public void setTeatarId(Long teatarId) {
        this.teatarId = teatarId;
    }

    public Long getRekvizitId() {
        return rekvizitId;
    }

    public void setRekvizitId(Long rekvizitId) {
        this.rekvizitId = rekvizitId;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
}
