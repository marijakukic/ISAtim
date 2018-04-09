package ftn.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Prijateljstvo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idKorisnik1;
    private Long idKorisnik2;
    private Boolean prijatelji;
    private Long zahtevPoslao;
    private Boolean poslatZahtev;


    public  Prijateljstvo(){}

    public Prijateljstvo(Long idKorisnik1, Long idKorisnik2, Boolean prijatelji, Long zahtevPoslao, Boolean poslatZahtev) {
        this.idKorisnik1 = idKorisnik1;
        this.idKorisnik2 = idKorisnik2;
        this.prijatelji = prijatelji;
        this.zahtevPoslao = zahtevPoslao;
        this.poslatZahtev = poslatZahtev;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdKorisnik1() {
        return idKorisnik1;
    }

    public void setIdKorisnik1(Long idKorisnik1) {
        this.idKorisnik1 = idKorisnik1;
    }

    public Long getIdKorisnik2() {
        return idKorisnik2;
    }

    public void setIdKorisnik2(Long idKorisnik2) {
        this.idKorisnik2 = idKorisnik2;
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

    public Boolean getPoslatZahtev() {
        return poslatZahtev;
    }

    public void setPoslatZahtev(Boolean poslatZahtev) {
        this.poslatZahtev = poslatZahtev;
    }
}
