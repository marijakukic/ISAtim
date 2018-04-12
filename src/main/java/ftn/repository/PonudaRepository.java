package ftn.repository;

import ftn.model.Ponuda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PonudaRepository extends JpaRepository<Ponuda, Long> {

    Ponuda findByKorisnikIdAndRekvizitId(Long kId, Long rId);

}
