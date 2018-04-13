package ftn.service;

import ftn.model.Constants;
import ftn.model.Korisnik;
import org.springframework.beans.factory.annotation.Autowired;
import ftn.repository.KorisnikRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class KorisnikService {

    @Autowired
    private KorisnikRepository korisnikRepository;

    public static Korisnik aktivanKorisnik;

    public Korisnik save(Korisnik korisnik){
        return korisnikRepository.save(korisnik);
    }

    public Korisnik findByEmail(String email){
        Korisnik korisnik = korisnikRepository.findByEmail(email);
        return  korisnik;
    }


    public Integer setActivated(Boolean potvrdjenMail, String email) {
        return korisnikRepository.setActivatedForKorisnik(potvrdjenMail, email);
    }

    public Korisnik findUserDetails(Long id) {
        return korisnikRepository.findOne(id);
    }


    public Collection<Korisnik> getAllRegUsersExceptMe(Long id) {
        return korisnikRepository.findByIdNotAndTipKorisnika(id, Constants.REGISTROVAN_KORISNIK_TIP);
    }

    public Collection<Korisnik> getAllRegUsersExceptMeByName(Long id, String ime) {
        return korisnikRepository.findByIdNotAndImeAndTipKorisnika(id, ime, Constants.REGISTROVAN_KORISNIK_TIP);
    }

    public Collection<Korisnik> getAllRegUsersExceptMeByNameAndSurname(Long id, String ime, String prezime) {
        return korisnikRepository.findByIdNotAndImeAndPrezimeAndTipKorisnika(id, ime, prezime, Constants.REGISTROVAN_KORISNIK_TIP);
    }

    public Collection<Korisnik> getAllRegUsersExceptMeBySurname(Long id, String prezime) {
        return korisnikRepository.findByIdNotAndPrezimeAndTipKorisnika(id, prezime, Constants.REGISTROVAN_KORISNIK_TIP);
    }
}
