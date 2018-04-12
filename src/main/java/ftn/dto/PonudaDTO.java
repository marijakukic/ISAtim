package ftn.dto;

import ftn.model.Korisnik;

public class PonudaDTO {

    private Long id;

    private Long teatarId;

    private Korisnik korisnik;

    private Long rekvizitId;

    private Double cena;

    private Boolean poslata;

    private Boolean odabrana;

    public PonudaDTO() {
    }

    public PonudaDTO(Long id, Long teatarId, Korisnik korisnik, Long rekvizitId, Double cena, Boolean poslata, Boolean odabrana) {
        this.id = id;
        this.teatarId = teatarId;
        this.korisnik = korisnik;
        this.rekvizitId = rekvizitId;
        this.cena = cena;
        this.poslata = poslata;
        this.odabrana = odabrana;
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

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
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
