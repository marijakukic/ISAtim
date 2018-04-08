package ftn.service;

import ftn.model.Korisnik;
import ftn.model.Projekcija;
import ftn.repository.ProjekcijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

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

}
