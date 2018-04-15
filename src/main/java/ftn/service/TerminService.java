package ftn.service;

import ftn.model.Projekcija;
import ftn.model.Termin;
import ftn.repository.TerminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TerminService {

    @Autowired
    private TerminRepository terminRepository;

    public Termin save(Termin termin) {
        return terminRepository.save(termin);
    }

    public void delete(Termin termin) {
        terminRepository.delete(termin);
    }

    public void delete(Long id) {
        terminRepository.delete(id);
    }

    public Termin findOne(Long id) {
        return terminRepository.findOne(id);
    }

    public Collection<Termin> findByProjekcija(Projekcija projekcija) {
        return terminRepository.findByProjekcija(projekcija);
    }
}
