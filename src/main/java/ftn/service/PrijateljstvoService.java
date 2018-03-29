package ftn.service;

import ftn.model.Prijateljstvo;
import ftn.repository.PrijateljstvoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PrijateljstvoService  {

    @Autowired
    PrijateljstvoRepository prijateljstvoRepository;

    public Collection<Prijateljstvo> listaPrijatelja(Long userId){
        return prijateljstvoRepository.findByIdKorisnik1(userId);
    }
}
