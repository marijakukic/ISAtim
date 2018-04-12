package ftn.repository;

import ftn.model.Rekvizit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RekvizitRepository extends JpaRepository<Rekvizit, Long>{

    Collection<Rekvizit> findByTeatarId(Long teatarId);

    Collection<Rekvizit> findByTeatarIdAndStanje(Long teatarId, String stanje);

    Collection<Rekvizit> findByTeatarIdAndStanjeAndKorisnikId(Long teatarId, String stanje, Long korisnikId);

    Collection<Rekvizit> findByTeatarIdAndStanjeAndKorisnikIdNot(Long teatarId, String stanje, Long korisnikId);

}
