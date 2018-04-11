package ftn.service;

import ftn.model.Rezervacija;
import ftn.model.Termin;
import ftn.repository.RezervacijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RezervacijaService {

    @Autowired
    private RezervacijaRepository rezervacijaRepository;

    public Rezervacija findOne(Long id) {
        return rezervacijaRepository.findOne(id);
    }

    public Collection<Rezervacija> findByTermin(Termin termin) {
        return rezervacijaRepository.findByTermin(termin);
    }


}
