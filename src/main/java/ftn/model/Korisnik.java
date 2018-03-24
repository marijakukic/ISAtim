package ftn.model;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class Korisnik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String ime;
    public String prezime;

    @Column(unique = true)
    public String email;

    public String lozinka;

    public String mestoStanovanja;
    public String adresa;
    public String tipKorisnika;
    private boolean potvrdjenMail;

    public Korisnik() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getMestoStanovanja() {
        return mestoStanovanja;
    }

    public void setMestoStanovanja(String mestoStanovanja) {
        this.mestoStanovanja = mestoStanovanja;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTipKorisnika() {
        return tipKorisnika;
    }

    public void setTipKorisnika(String tipKorisnika) {
        this.tipKorisnika = tipKorisnika;
    }

    public boolean isPotvrdjenMail() {
        return potvrdjenMail;
    }

    public void setPotvrdjenMail(boolean potvrdjenMail) {
        this.potvrdjenMail = potvrdjenMail;
    }
}
