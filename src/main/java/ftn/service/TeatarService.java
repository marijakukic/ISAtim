package ftn.service;

import ftn.model.Korisnik;
import ftn.model.Teatar;
import ftn.repository.TeatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeatarService {

    @Autowired
    TeatarRepository teatarRepository;

    public void save(Teatar teatar){
        teatarRepository.save(teatar);
    }
}
