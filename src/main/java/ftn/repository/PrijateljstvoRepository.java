package ftn.repository;

import ftn.model.Prijateljstvo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PrijateljstvoRepository  extends JpaRepository<Prijateljstvo,Long>{

    Collection<Prijateljstvo> findByIdKorisnik1(Long idKorisnik1);

    Collection<Prijateljstvo> findByIdKorisnik1AndIdKorisnik2AndPoslatZahtev(Long id1, Long id2, Boolean pz);

    Prijateljstvo findByIdKorisnik1AndIdKorisnik2(Long id1, Long id2);


}
