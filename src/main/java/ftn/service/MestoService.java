package ftn.service;

import ftn.model.Mesto;
import ftn.repository.MestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MestoService {

    @Autowired
    private MestoRepository mestoRepository;

    public Mesto fincOne(Long id) {
        return mestoRepository.findOne(id);
    }

    public Mesto save(Mesto mesto) {
        return mestoRepository.save(mesto);
    }

    public void deleteMesto(Long id) { mestoRepository.delete(id); }

}
