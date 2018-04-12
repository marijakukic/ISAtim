package ftn.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ponuda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long teatarId;

    private Long korisnikId;

    private Long rekvizitId;

    private Double cena;

    private Boolean poslata;

    private Boolean odabrana;

    public Ponuda() {
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

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Long getRekvizitId() {
        return rekvizitId;
    }

    public void setRekvizitId(Long rekvizitId) {
        this.rekvizitId = rekvizitId;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public Boolean getPoslata() {
        return poslata;
    }

    public void setPoslata(Boolean poslata) {
        this.poslata = poslata;
    }

    public Boolean getOdabrana() {
        return odabrana;
    }

    public void setOdabrana(Boolean odabrana) {
        this.odabrana = odabrana;
    }
}
