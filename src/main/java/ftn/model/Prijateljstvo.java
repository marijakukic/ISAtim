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

    public Long idKorisnik1;
    public Long idKorisnik2;
    public boolean prijatelji;

    public Prijateljstvo(Long idKorisnik1, Long idKorisnik2) {
        this.idKorisnik1 = idKorisnik1;
        this.idKorisnik2 = idKorisnik2;
    }
    public  Prijateljstvo(){}

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

    public boolean isPrijatelji() {
        return prijatelji;
    }

    public void setPrijatelji(boolean prijatelji) {
        this.prijatelji = prijatelji;
    }
}
