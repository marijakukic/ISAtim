package ftn.service;

import ftn.model.Korisnik;
import org.springframework.beans.factory.annotation.Autowired;
import ftn.repository.KorisnikRepository;
import org.springframework.stereotype.Service;

@Service
public class KorisnikService {

    @Autowired
    KorisnikRepository korisnikRepository;

    public void save(Korisnik korisnik){
        korisnikRepository.save(korisnik);
    }

    public Korisnik findByEmail(String email){
        Korisnik korisnik = korisnikRepository.findByEmail(email);
        return  korisnik;
    }


    public Integer setActivated(Boolean potvrdjenMail, String email) {
        return korisnikRepository.setActivatedForKorisnik(potvrdjenMail, email);
    }
}
