package ftn.service;

import ftn.model.Teatar;
import ftn.repository.TeatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TeatarService {

    @Autowired
    TeatarRepository teatarRepository;

    public void save(Teatar teatar){
        teatarRepository.save(teatar);
    }

    public Collection<Teatar> getAllBioskop(String tip){
        return teatarRepository.findByTip(tip);

   }
}