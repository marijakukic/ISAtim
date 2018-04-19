package ftn.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Rekvizit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long teatarId;

    private String naziv;

    private String opis;

    private Double cena;

    private String stanje;

    private String slika;

    private String datum;

    private Long korisnikId;

    private Boolean odobren;

    private Boolean zavrsenaLicitacija;

    public Rekvizit() {

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

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public String getStanje() {
        return stanje;
    }

    public void setStanje(String stanje) {
        this.stanje = stanje;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Boolean getOdobren() {
        return odobren;
    }

    public void setOdobren(Boolean odobren) {
        this.odobren = odobren;
    }

    public Boolean getZavrsenaLicitacija() {
        return zavrsenaLicitacija;
    }

    public void setZavrsenaLicitacija(Boolean zavrsenaLicitacija) {
        this.zavrsenaLicitacija = zavrsenaLicitacija;
    }
}
