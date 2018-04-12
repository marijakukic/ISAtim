package ftn.service;

import ftn.model.Ponuda;
import ftn.repository.PonudaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PonudaService {

    @Autowired
    private PonudaRepository ponudaRepository;

    public Ponuda findOne(Long id) {
        return ponudaRepository.findOne(id);
    }

    public Ponuda save(Ponuda ponuda) {
        return ponudaRepository.save(ponuda);
    }

    public void delete(Long id) {
        ponudaRepository.delete(id);
    }

    public Ponuda findByKorisnikIdAndRekvizitId(Long kId, Long rId) {
        return ponudaRepository.findByKorisnikIdAndRekvizitId(kId, rId);
    }

    public Collection<Ponuda> findByRekvizitId(Long id) {
        return ponudaRepository.findByRekvizitId(id);
    }

    public Collection<Ponuda> findByRekvizitIdAndIdNot(Long id, Long idPon) {
        return ponudaRepository.findByRekvizitIdAndIdNot(id, idPon);
    }



}
