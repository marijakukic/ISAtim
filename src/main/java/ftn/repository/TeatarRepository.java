package ftn.repository;

import ftn.model.Teatar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface TeatarRepository extends JpaRepository<Teatar,Long> {

    public Collection<Teatar> findByTip (String tip);
}
