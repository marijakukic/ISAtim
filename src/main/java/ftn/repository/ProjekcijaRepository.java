package ftn.repository;

import ftn.model.Projekcija;
import ftn.model.Rezervacija;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ProjekcijaRepository extends JpaRepository<Projekcija, Long> {

    Collection<Projekcija> findByTeatarIdAndDatum(Long id, String datum);

    Collection<Projekcija> findByTeatarIdAndDatumGreaterThanEqual(Long id, String todaysDate);

}
