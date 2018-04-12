package ftn.service;

import ftn.model.Rekvizit;
import ftn.repository.RekvizitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RekvizitService {

    @Autowired
    private RekvizitRepository rekvizitRepository;


    public Rekvizit findOne(Long id) {
        return rekvizitRepository.findOne(id);
    }

    public Rekvizit save(Rekvizit rekvizit) {
        return rekvizitRepository.save(rekvizit);
    }

    public Collection<Rekvizit> findByTeatarId(Long teatarId){
        return rekvizitRepository.findByTeatarId(teatarId);
    }

    public Collection<Rekvizit> findByTeatarIdAndStanjeAndKorisnikIdNot(Long teatarId, String stanje, Long korisnikId) {
        return rekvizitRepository.findByTeatarIdAndStanjeAndKorisnikIdNot(teatarId, stanje, korisnikId);
    }

    public Collection<Rekvizit> findByTeatarIdAndStanjeAndKorisnikId(Long teatarId, String stanje, Long korisnikId) {
        return rekvizitRepository.findByTeatarIdAndStanjeAndKorisnikId(teatarId, stanje, korisnikId);
    }

    public Collection<Rekvizit> findByTeatarIdAndStanje(Long teatarId, String stanje) {
        return rekvizitRepository.findByTeatarIdAndStanje(teatarId, stanje);
    }

}
