package ftn.dto;

import ftn.model.Mesto;
import ftn.model.Teatar;
import ftn.model.Termin;

public class RezervacijaDTO {

    private Long id;

    private Teatar teatar;

    private ProjekcijaDTO projekcija;

    private Termin termin;

    private Mesto mesto;

    private Long korisnikId;

    private Double cenaSaPopustom;

    public RezervacijaDTO() {

    }

    public RezervacijaDTO(Long id, Teatar teatar, ProjekcijaDTO projekcija, Termin termin, Mesto mesto, Long korisnikId, Double cenaSaPopustom) {
        this.id = id;
        this.teatar = teatar;
        this.projekcija = projekcija;
        this.termin = termin;
        this.mesto = mesto;
        this.korisnikId = korisnikId;
        this.cenaSaPopustom = cenaSaPopustom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Teatar getTeatar() {
        return teatar;
    }

    public void setTeatar(Teatar teatar) {
        this.teatar = teatar;
    }

    public ProjekcijaDTO getProjekcija() {
        return projekcija;
    }

    public void setProjekcija(ProjekcijaDTO projekcija) {
        this.projekcija = projekcija;
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

    public Double getCenaSaPopustom() {
        return cenaSaPopustom;
    }

    public void setCenaSaPopustom(Double cenaSaPopustom) {
        this.cenaSaPopustom = cenaSaPopustom;
    }
}
