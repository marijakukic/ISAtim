package ftn.repository;

import ftn.model.Prijateljstvo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PrijateljstvoRepository  extends JpaRepository<Prijateljstvo,Long>{

    public Collection<Prijateljstvo> findByIdKorisnik1(Long idKorisnik1);
}
