package ftn.repository;

import ftn.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Collection;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long>{

    Korisnik findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update Korisnik k set k.potvrdjenMail = ?1 where k.email = ?2")
    Integer setActivatedForKorisnik(Boolean potvrdjenMail, String email);

    Collection<Korisnik> findByIdNot(Long id);

    Collection<Korisnik> findByIdNotAndIme(Long id, String ime);

    Collection<Korisnik> findByIdNotAndPrezime(Long id, String prezime);

    Collection<Korisnik> findByIdNotAndImeAndPrezime(Long id, String ime, String prezime);

}
