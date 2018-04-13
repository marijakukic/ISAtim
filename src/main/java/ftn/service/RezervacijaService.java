package ftn.service;

import ftn.dto.ProjekcijaDTO;
import ftn.dto.RezervacijaDTO;
import ftn.model.*;
import ftn.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Service
public class RezervacijaService {

    @Autowired
    private RezervacijaRepository rezervacijaRepository;

    @Autowired
    private TeatarRepository teatarRepository;

    @Autowired
    private ProjekcijaRepository projekcijaRepository;

    @Autowired
    private TerminRepository terminRepository;

    @Autowired
    private MestoRepository mestoRepository;

    @Autowired
    private SkalaClanstvaRepository skalaClanstvaRepository;

    public Rezervacija save(Rezervacija rezervacija) {
        return rezervacijaRepository.save(rezervacija);
    }

    public void delete(Long id) {
        rezervacijaRepository.delete(id);
    }

    public Rezervacija findOne(Long id) {
        return rezervacijaRepository.findOne(id);
    }

    public Collection<Rezervacija> findByTermin(Termin termin) {
        return rezervacijaRepository.findByTermin(termin);
    }

    public Collection<Rezervacija> findByKorisnikId(Long id) {
        return rezervacijaRepository.findByKorisnikId(id);
    }

    public int bodoviKorisnika(Long korisnikId) throws ParseException {
        ArrayList<RezervacijaDTO> prethodneRezervacije = new ArrayList<>();

        String today = new SimpleDateFormat("yyyyMMdd HH:mm").format(new Date());

        Collection<Rezervacija> sveRezervacije = rezervacijaRepository.findByKorisnikId(korisnikId);
        for (Rezervacija r : sveRezervacije) {
            Teatar teatar = teatarRepository.findOne(r.getTeatarId());
            Projekcija projekcija = projekcijaRepository.findOne(r.getProjekcijaId());
            ProjekcijaDTO projekcijaDTO = new ProjekcijaDTO(projekcija.getId(), projekcija.getTeatarId(), projekcija.getFilm(), projekcija.getDatum(), projekcija.getTermini());
            Termin termin = terminRepository.findOne(r.getTermin().getId());
            Mesto mesto = mestoRepository.findOne(r.getMesto().getId());
            RezervacijaDTO rDTO = new RezervacijaDTO(r.getId(), teatar, projekcijaDTO, termin, mesto, korisnikId);

            int minutaDoPocetkaRezervacije = DateService.diffInMinutes(today, projekcija.getDatum(), termin.getVreme());

            if (minutaDoPocetkaRezervacije < 0) {
                prethodneRezervacije.add(rDTO);
            }

        }

        SkalaClanstva skalaClanstva = skalaClanstvaRepository.findOne(1l);

        return prethodneRezervacije.size() * skalaClanstva.getBodoviZaPosetu();
    }



}
