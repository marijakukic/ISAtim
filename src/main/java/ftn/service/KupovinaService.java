package ftn.service;

import ftn.model.Kupovina;
import ftn.repository.KupovinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KupovinaService {

    @Autowired
    private KupovinaRepository kupovinaRepository;

    public Kupovina save(Kupovina kupovina) {
        return kupovinaRepository.save(kupovina);
    }

}
