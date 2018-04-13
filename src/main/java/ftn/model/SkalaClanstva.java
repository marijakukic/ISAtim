package ftn.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class SkalaClanstva implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer bronzani;

    private Integer srebrni;

    private Integer zlatni;

    private Integer bronzaniPopust;

    private Integer srebrniPopust;

    private Integer zlatniPopust;

    private Integer bodoviZaPosetu;

    public SkalaClanstva() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBronzani() {
        return bronzani;
    }

    public void setBronzani(Integer bronzani) {
        this.bronzani = bronzani;
    }

    public Integer getSrebrni() {
        return srebrni;
    }

    public void setSrebrni(Integer srebrni) {
        this.srebrni = srebrni;
    }

    public Integer getZlatni() {
        return zlatni;
    }

    public void setZlatni(Integer zlatni) {
        this.zlatni = zlatni;
    }

    public Integer getBronzaniPopust() {
        return bronzaniPopust;
    }

    public void setBronzaniPopust(Integer bronzaniPopust) {
        this.bronzaniPopust = bronzaniPopust;
    }

    public Integer getSrebrniPopust() {
        return srebrniPopust;
    }

    public void setSrebrniPopust(Integer srebrniPopust) {
        this.srebrniPopust = srebrniPopust;
    }

    public Integer getZlatniPopust() {
        return zlatniPopust;
    }

    public void setZlatniPopust(Integer zlatniPopust) {
        this.zlatniPopust = zlatniPopust;
    }

    public Integer getBodoviZaPosetu() {
        return bodoviZaPosetu;
    }

    public void setBodoviZaPosetu(Integer bodoviZaPosetu) {
        this.bodoviZaPosetu = bodoviZaPosetu;
    }
}
