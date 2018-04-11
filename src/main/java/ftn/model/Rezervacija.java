package ftn.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Rezervacija implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long teatarId;

    private Long projekcijaId;

    @ManyToOne
    private Termin termin;

    @ManyToOne
    private Mesto mesto;

    private Long korisnikId;

    public Rezervacija() {

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

    public Long getProjekcijaId() {
        return projekcijaId;
    }

    public void setProjekcijaId(Long projekcijaId) {
        this.projekcijaId = projekcijaId;
    }

    public Termin getTermin() {
        return termin;
    }

    public void setTermin(Termin termin) {
        this.termin = termin;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }
}
