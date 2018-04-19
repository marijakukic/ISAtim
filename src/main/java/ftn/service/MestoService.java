package ftn.service;

import ftn.model.Korisnik;
import ftn.model.Mesto;
import ftn.repository.MestoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MestoService {

    @Autowired
    private MestoRepository mestoRepository;

    public Mesto findOne(Long id) {
        return mestoRepository.findOne(id);
    }

    public Mesto save(Mesto mesto) {
        return mestoRepository.save(mesto);
    }

    public void deleteMesto(Long id) { mestoRepository.delete(id); }
    
    public List<Mesto> findAll(){
    	return mestoRepository.findAll();
    }

}
