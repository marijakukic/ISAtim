package ftn.repository;

import ftn.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long>{

    Korisnik findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update Korisnik k set k.potvrdjenMail = ?1 where k.email = ?2")
    public Integer setActivatedForKorisnik(Boolean potvrdjenMail, String email);

}
