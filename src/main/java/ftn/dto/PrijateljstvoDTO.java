package ftn.dto;

import ftn.model.Korisnik;

public class PrijateljstvoDTO {

    private Korisnik receiver;
    private Boolean prijatelji;
    private Long zahtevPoslao;

    public PrijateljstvoDTO() {
    }

    public PrijateljstvoDTO(Korisnik receiver, Boolean prijatelji, Long zahtevPoslao) {
        this.receiver = receiver;
        this.prijatelji = prijatelji;
        this.zahtevPoslao = zahtevPoslao;
    }


    public Korisnik getReceiver() {
        return receiver;
    }

    public void setReceiver(Korisnik receiver) {
        this.receiver = receiver;
    }

    public Boolean getPrijatelji() {
        return prijatelji;
    }

    public void setPrijatelji(Boolean prijatelji) {
        this.prijatelji = prijatelji;
    }

    public Long getZahtevPoslao() {
        return zahtevPoslao;
    }

    public void setZahtevPoslao(Long zahtevPoslao) {
        this.zahtevPoslao = zahtevPoslao;
    }
}
