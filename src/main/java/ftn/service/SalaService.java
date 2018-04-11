package ftn.service;

import ftn.model.Mesto;
import ftn.model.Sala;
import ftn.repository.MestoRepository;
import ftn.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private MestoRepository mestoRepository;

    public Sala save(Sala sala) {
        return salaRepository.save(sala);
    }

    public Collection<Mesto> getAllSalaSeats(Long salaId) {
        return mestoRepository.findBySalaId(salaId);
    }

}
