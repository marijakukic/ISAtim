package ftn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Termin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Projekcija projekcija;

    private Long salaId;

    private String salaNaziv;

    private String vreme;

    private Double cena;

    public Termin() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Projekcija getProjekcija() {
        return projekcija;
    }

    @JsonIgnore
    public void setProjekcija(Projekcija projekcija) {
        this.projekcija = projekcija;
    }

    public Long getSalaId() {
        return salaId;
    }

    public void setSalaId(Long salaId) {
        this.salaId = salaId;
    }

    public String getSalaNaziv() {
        return salaNaziv;
    }

    public void setSalaNaziv(String salaNaziv) {
        this.salaNaziv = salaNaziv;
    }

    public String getVreme() {
        return vreme;
    }

    public void setVreme(String vreme) {
        this.vreme = vreme;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }
}
