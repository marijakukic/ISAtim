package ftn.dto;

import ftn.model.Korisnik;

public class PrijateljstvoDTO {

    private Korisnik receiver;
    private Boolean prijatelji;

    public PrijateljstvoDTO() {
    }

    public PrijateljstvoDTO(Korisnik receiver, Boolean prijatelji) {
        this.receiver = receiver;
        this.prijatelji = prijatelji;
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
}
