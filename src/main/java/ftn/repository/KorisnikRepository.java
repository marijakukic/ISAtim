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

    Collection<Korisnik> findByIdNotAndTipKorisnika(Long id, String tip);

    Collection<Korisnik> findByIdNotAndImeAndTipKorisnika(Long id, String ime, String tip);

    Collection<Korisnik> findByIdNotAndPrezimeAndTipKorisnika(Long id, String prezime, String tip);

    Collection<Korisnik> findByIdNotAndImeAndPrezimeAndTipKorisnika(Long id, String ime, String prezime, String tip);

}
