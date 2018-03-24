package ftn.repository;

import ftn.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long>{

    Korisnik findByEmail(String email);

}
