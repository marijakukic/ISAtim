package ftn.service;

import ftn.model.Prijateljstvo;
import ftn.repository.PrijateljstvoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PrijateljstvoService  {

    @Autowired
    private PrijateljstvoRepository prijateljstvoRepository;

    public Prijateljstvo save(Prijateljstvo prijateljstvo) {
        return prijateljstvoRepository.save(prijateljstvo);
    }

    public void delete(Prijateljstvo prijateljstvo) {
        prijateljstvoRepository.delete(prijateljstvo);
    }

    public Prijateljstvo findBySenderAndRecieverIds(Long ids, Long idr) {
        return prijateljstvoRepository.findByIdKorisnik1AndIdKorisnik2(ids, idr);
    }

    public Collection<Prijateljstvo> listaPrijatelja(Long userId){
        return prijateljstvoRepository.findByIdKorisnik1(userId);
    }

    public Collection<Prijateljstvo> vecPrijatelji(Long sender, Long receiver) {
        return prijateljstvoRepository.findByIdKorisnik1AndIdKorisnik2AndPoslatZahtev(sender, receiver, true);
    }
}
