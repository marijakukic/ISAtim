package ftn.dto;

import ftn.model.Film;

public class ProjekcijaDTO {

    private Film film;

    private String datum;

    private String termin;

    public ProjekcijaDTO() {
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

    public String getTermin() {
        return termin;
    }

    public void setTermin(String termin) {
        this.termin = termin;
    }
}
