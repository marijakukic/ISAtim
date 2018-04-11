package ftn.repository;

import ftn.model.Rezervacija;
import ftn.model.Termin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {

    Collection<Rezervacija> findByTermin(Termin termin);

}
