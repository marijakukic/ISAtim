package ftn.repository;

import ftn.model.Teatar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface TeatarRepository extends JpaRepository<Teatar,Long> {

    Collection<Teatar> findByTip(String tip);

    Collection<Teatar> findByTipAndNaziv(String tip, String naziv);
}
