package ftn.repository;

import ftn.model.Ponuda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PonudaRepository extends JpaRepository<Ponuda, Long> {

    Ponuda findByKorisnikIdAndRekvizitId(Long kId, Long rId);

    Collection<Ponuda> findByRekvizitId(Long id);

    Collection<Ponuda> findByRekvizitIdAndIdNot(Long id, Long ponId);

}
