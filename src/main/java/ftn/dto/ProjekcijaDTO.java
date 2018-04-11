package ftn.dto;

import ftn.model.Film;
import ftn.model.Termin;

import javax.persistence.*;
import java.util.Collection;

public class ProjekcijaDTO {

    private Long id;

    private Long teatarId;

    private Film film;

    private String datum;

    private Collection<Termin> termini;

    public ProjekcijaDTO() {
    }

    public ProjekcijaDTO(Long id, Long teatarId, Film film, String datum, Collection<Termin> termini) {
        this.id = id;
        this.teatarId = teatarId;
        this.film = film;
        this.datum = datum;
        this.termini = termini;
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

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Collection<Termin> getTermini() {
        return termini;
    }

    public void setTermini(Collection<Termin> termini) {
        this.termini = termini;
    }
}
