package ftn.repository;

import ftn.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface SalaRepository extends JpaRepository<Sala, Long> {

    Collection<Sala> findByTeatarId(Long teatarId);

}
