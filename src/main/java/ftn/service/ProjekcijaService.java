package ftn.service;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.model.Korisnik;
import ftn.model.Projekcija;
import ftn.repository.ProjekcijaRepository;

@Service
public class ProjekcijaService {

    @Autowired
    private ProjekcijaRepository projekcijaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Projekcija save(Projekcija projekcija) {
        return projekcijaRepository.save(projekcija);
    }

    public List<Korisnik> test() {
        TypedQuery<Korisnik> query = entityManager.createQuery("select k from Korisnik k", Korisnik.class);
        List<Korisnik> korisnikList = query.getResultList();
        return korisnikList;
    }

    public Projekcija findOne(Long id) {
        return projekcijaRepository.findOne(id);
    }

    public void delete(Projekcija projekcija) {
        projekcijaRepository.delete(projekcija);
    }

    public Collection<Projekcija> findByTeatarIdAndDatum(Long id, String datum) {
        return projekcijaRepository.findByTeatarIdAndDatum(id, datum);
    }

    public Collection<Projekcija> findByTeatarIdAndDatumGreaterThanEqual(Long id, String todaysDate){
        return projekcijaRepository.findByTeatarIdAndDatumGreaterThanEqual(id, todaysDate);
    }
    
    public List<Projekcija> findAll(){
    	return projekcijaRepository.findAll();
    }

}
