package ftn.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.model.Mesto;
import ftn.model.Sala;
import ftn.repository.MestoRepository;
import ftn.repository.SalaRepository;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private MestoRepository mestoRepository;

    public Sala save(Sala sala) {
        return salaRepository.save(sala);
    }

    public Sala findOne(Long id) {
        return salaRepository.findOne(id);
    }

    public Collection<Sala> findByTeatarId(Long teatarId) {
        return salaRepository.findByTeatarId(teatarId);
    }

    public Collection<Mesto> getAllSalaSeats(Long salaId) {
        return mestoRepository.findBySalaId(salaId);
    }
    
    public List<Sala> findAll(){
    	return salaRepository.findAll();
    }

}
