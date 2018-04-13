package ftn.service;

import ftn.model.Constants;
import ftn.model.SkalaClanstva;
import ftn.repository.SkalaClanstvaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkalaClanstvaService {

    @Autowired
    private SkalaClanstvaRepository skalaClanstvaRepository;

    public SkalaClanstva save(SkalaClanstva skalaClanstva) {
        return skalaClanstvaRepository.save(skalaClanstva);
    }

    public SkalaClanstva findOne(Long id) {
        return skalaClanstvaRepository.findOne(id);
    }

    public String getTitula(Integer bodovi) {

        SkalaClanstva skalaClanstva = skalaClanstvaRepository.findOne(1l);

        if (bodovi < skalaClanstva.getBronzani()) {
            return Constants.NOVI_CLAN;
        }
        else if (bodovi >= skalaClanstva.getBronzani() && bodovi < skalaClanstva.getSrebrni()) {
            return Constants.BRONZANI_CLAN;
        }
        else if (bodovi >= skalaClanstva.getSrebrni() && bodovi < skalaClanstva.getZlatni()) {
            return Constants.SREBRNI_CLAN;
        }
        else if (bodovi >= skalaClanstva.getZlatni()) {
            return Constants.ZLATNI_CLAN;
        }
        else {
            return Constants.NOVI_CLAN;
        }

    }


}
