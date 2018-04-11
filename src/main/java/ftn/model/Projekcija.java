package ftn.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Projekcija implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long teatarId;

    @ManyToOne
    private Film film;

    private String datum;

    @OneToMany(mappedBy = "projekcija", fetch = FetchType.EAGER)
    private Collection<Termin> termini;

    public Projekcija() {
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
