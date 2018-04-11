package ftn.service;

import ftn.model.Termin;
import ftn.repository.TerminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TerminService {

    @Autowired
    private TerminRepository terminRepository;


    public Termin findOne(Long id) {
        return terminRepository.findOne(id);
    }

}
