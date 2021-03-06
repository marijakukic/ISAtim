package ftn.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.dto.ProjekcijaDTO;
import ftn.dto.RezervacijaDTO;
import ftn.model.Mesto;
import ftn.model.Projekcija;
import ftn.model.Rezervacija;
import ftn.model.SkalaClanstva;
import ftn.model.Teatar;
import ftn.model.Termin;
import ftn.repository.MestoRepository;
import ftn.repository.ProjekcijaRepository;
import ftn.repository.RezervacijaRepository;
import ftn.repository.SkalaClanstvaRepository;
import ftn.repository.TeatarRepository;
import ftn.repository.TerminRepository;

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

    @Transactional
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

    public Collection<Rezervacija> findByKorisnikIdIsNull() {
        return rezervacijaRepository.findByKorisnikIdIsNull();
    }

    public Collection<Rezervacija> findByProjekcijaId(Long id) {
        return rezervacijaRepository.findByProjekcijaId(id);
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
            RezervacijaDTO rDTO = new RezervacijaDTO(r.getId(), teatar, projekcijaDTO, termin, mesto, korisnikId, r.getCenaSaPopustom());

            int minutaDoPocetkaRezervacije = DateService.diffInMinutes(today, projekcija.getDatum(), termin.getVreme());

            if (minutaDoPocetkaRezervacije < 0) {
                prethodneRezervacije.add(rDTO);
            }

        }

        SkalaClanstva skalaClanstva = skalaClanstvaRepository.findOne(1l);

        return prethodneRezervacije.size() * skalaClanstva.getBodoviZaPosetu();
    }
    
    public List<Rezervacija> findAll(){
    	return rezervacijaRepository.findAll();
    }



}
