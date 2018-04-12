package ftn.service;

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


    public Collection<Korisnik> getAllUsersExceptMe(Long id) {
        return korisnikRepository.findByIdNot(id);
    }

    public Collection<Korisnik> getAllUsersExceptMeByName(Long id, String ime) {
        return korisnikRepository.findByIdNotAndIme(id, ime);
    }

    public Collection<Korisnik> getAllUsersExceptMeByNameAndSurname(Long id, String ime, String prezime) {
        return korisnikRepository.findByIdNotAndImeAndPrezime(id, ime, prezime);
    }

    public Collection<Korisnik> getAllUsersExceptMeBySurname(Long id, String prezime) {
        return korisnikRepository.findByIdNotAndPrezime(id, prezime);
    }
}
