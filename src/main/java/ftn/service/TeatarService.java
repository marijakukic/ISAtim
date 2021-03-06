package ftn.service;

import ftn.model.Segment;
import ftn.model.Teatar;
import ftn.repository.TeatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class TeatarService {

    @Autowired
    private TeatarRepository teatarRepository;

    public Teatar save(Teatar teatar){
        return teatarRepository.save(teatar);
    }

    public Teatar findOne(Long id){
        return  teatarRepository.findOne(id);
    }

    public Collection<Teatar> getAllBioskop(String tip){
        return teatarRepository.findByTip(tip);
    }

    public Collection<Teatar> findByTipAndNaziv(String tip, String naziv) {
        return teatarRepository.findByTipAndNaziv(tip, naziv);
    }

    public List<Teatar> findAll(){
    	return teatarRepository.findAll();
    }

}
