package ftn.service;

import ftn.model.Sala;
import ftn.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    public Sala save(Sala sala) {
        return salaRepository.save(sala);
    }

}
