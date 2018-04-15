package ftn.repository;

import ftn.model.Projekcija;
import ftn.model.Termin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface TerminRepository extends JpaRepository<Termin, Long> {

    Collection<Termin> findByProjekcija(Projekcija projekcija);

}
