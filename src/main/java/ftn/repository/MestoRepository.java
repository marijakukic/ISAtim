package ftn.repository;

import ftn.model.Mesto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface MestoRepository extends JpaRepository<Mesto, Long> {

    Collection<Mesto> findBySalaId(Long salaId);

}
