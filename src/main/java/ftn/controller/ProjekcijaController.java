package ftn.controller;

import ftn.dto.MestoDTO;
import ftn.dto.ProjekcijaDTO;
import ftn.dto.RezervacijaDTO;
import ftn.model.*;
import ftn.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProjekcijaController {

    @Autowired
    private ProjekcijaService projekcijaService;

    @Autowired
    private SalaService salaService;

    @Autowired
    private TerminService terminService;

    @Autowired
    private RezervacijaService rezervacijaService;

    @Autowired
    private SegmentService segmentService;

    @Autowired
    private MestoService mestoService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private TeatarService teatarService;

    @Autowired
    private FilmService filmService;

    @RequestMapping(
            value    = "/projekcija/test",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Korisnik>> sviBioskopi() {
        System.out.println("Usao u metodu");
        List<Korisnik> projekcije = projekcijaService.test();
        return new ResponseEntity<List<Korisnik>>(projekcije, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/projekcija/getProjectionsForDate/{teatarId}/{date}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<ProjekcijaDTO>> getProjectionsForDate(@PathVariable Long teatarId,@PathVariable String date) throws ParseException {

        String formattedDate = DateService.getFormattedDate(date);
        ArrayList<Projekcija> projekcije = (ArrayList<Projekcija>) projekcijaService.findByTeatarIdAndDatum(teatarId, formattedDate);

        ArrayList<ProjekcijaDTO> projekcijeDTO = new ArrayList<>();
        for (Projekcija projekcija : projekcije) {
            projekcijeDTO.add(new ProjekcijaDTO(projekcija.getId(), projekcija.getTeatarId(), projekcija.getFilm(), projekcija.getDatum(), projekcija.getTermini()));
        }

        return new ResponseEntity<>(projekcijeDTO, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/projekcija/aktivne/teatar/{teatarId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<ProjekcijaDTO>> getAktivneProjekcijeUTeatru(@PathVariable Long teatarId) throws ParseException {

        String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
        ArrayList<Projekcija> projekcije = (ArrayList<Projekcija>) projekcijaService.findByTeatarIdAndDatumGreaterThanEqual(teatarId, today);

        ArrayList<ProjekcijaDTO> projekcijeDTO = new ArrayList<>();
        for (Projekcija projekcija : projekcije) {
            projekcijeDTO.add(new ProjekcijaDTO(projekcija.getId(), projekcija.getTeatarId(), projekcija.getFilm(), projekcija.getDatum(), projekcija.getTermini()));
        }

        return new ResponseEntity<>(projekcijeDTO, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/projekcija/obrisi/{projekcijaId}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> obrisiProjekciju(@PathVariable Long projekcijaId) throws ParseException {

        String today = new SimpleDateFormat("yyyyMMdd HH:mm").format(new Date());
        Integer aktivneRezervacije = 0;
        Projekcija projekcija = projekcijaService.findOne(projekcijaId);

        Collection<Rezervacija> sveRezervacije = rezervacijaService.findByProjekcijaId(projekcijaId);
        for (Rezervacija r : sveRezervacije) {
            Termin termin = terminService.findOne(r.getTermin().getId());

            int minutaDoPocetkaRezervacije = DateService.diffInMinutes(today, projekcija.getDatum(), termin.getVreme());

            if (minutaDoPocetkaRezervacije >= 0) {
                ++aktivneRezervacije;
            }

        }

        if (aktivneRezervacije == 0) {
            Collection<Termin> termini = terminService.findByProjekcija(projekcija);
            for (Termin termin : termini) {
                terminService.delete(termin);
            }
            projekcijaService.delete(projekcija);
            return new ResponseEntity<>( 1, HttpStatus.OK);     //uspesno obrisana projekcija
        }

        //nije moguce obrisati projekciju => posalji 0
        return new ResponseEntity<>( 0, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/projekcija/izmena/omogucena/{projekcijaId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> omogucenaIzmenaProjekcije(@PathVariable Long projekcijaId) throws ParseException {

        String today = new SimpleDateFormat("yyyyMMdd HH:mm").format(new Date());
        Integer aktivneRezervacije = 0;
        Projekcija projekcija = projekcijaService.findOne(projekcijaId);

        Collection<Rezervacija> sveRezervacije = rezervacijaService.findByProjekcijaId(projekcijaId);
        for (Rezervacija r : sveRezervacije) {
            Termin termin = terminService.findOne(r.getTermin().getId());

            int minutaDoPocetkaRezervacije = DateService.diffInMinutes(today, projekcija.getDatum(), termin.getVreme());

            if (minutaDoPocetkaRezervacije >= 0) {
                ++aktivneRezervacije;
            }
        }
        if (aktivneRezervacije == 0) {
            return new ResponseEntity<>(1, HttpStatus.OK);
        }

        return new ResponseEntity<>(0, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/projekcija/getAllFreeSeats/{teatarId}/{terminId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<MestoDTO>> getAllFreeSeats(@PathVariable Long teatarId, @PathVariable Long terminId){

        Termin termin = terminService.findOne(terminId);

        ArrayList<Mesto> mesta = (ArrayList<Mesto>) salaService.getAllSalaSeats(termin.getSalaId());
        ArrayList<Rezervacija> rezervacije = (ArrayList<Rezervacija>) rezervacijaService.findByTermin(termin);

        //izbacuju se vec rezervisana mesta
        for (Rezervacija rezervacija : rezervacije) {
            mesta.remove(rezervacija.getMesto());
        }

        ArrayList<MestoDTO> mestaDTO = new ArrayList<>();
        for (Mesto mesto : mesta) {
            MestoDTO mestoDTO = new MestoDTO(mesto.getId(), mesto.getNaziv(), segmentService.findOne(mesto.getSegmentId()), mesto.getSalaId(), mesto.getX(), mesto.getY());
            mestaDTO.add(mestoDTO);
        }

        return new ResponseEntity<>(mestaDTO, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/rezervacija/saveRezervacija/{terminId}/{mestoId}/{korisnikId}/{poziv}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rezervacija> registration (@PathVariable Long terminId, @PathVariable Long mestoId, @PathVariable Long korisnikId, @PathVariable Boolean poziv) throws MessagingException {

        Termin termin = terminService.findOne(terminId);
        Mesto mesto = mestoService.findOne(mestoId);
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setTermin(termin);
        rezervacija.setMesto(mesto);
        rezervacija.setKorisnikId(korisnikId);
        rezervacija.setProjekcijaId(termin.getProjekcija().getId());
        rezervacija.setTeatarId(termin.getProjekcija().getTeatarId());

        Rezervacija savedRezervacija = rezervacijaService.save(rezervacija);

        Korisnik korisnik = korisnikService.findUserDetails(savedRezervacija.getKorisnikId());
        Teatar teatar = teatarService.findOne(savedRezervacija.getTeatarId());
        if (poziv) {
            MailSending.sendMail("boxboux@gmail.com", "Otkazivanje rezervacije", "http://localhost:9000/rezervacija/otkaziRezervaciju/"+savedRezervacija.getId());
        }
        else {
            MailSending.sendMail("boxboux@gmail.com", "Informacije o rezervaciji", "Rezervisali ste mesto: " + savedRezervacija.getMesto().getNaziv() + " u " + teatar.getNaziv() );

        }

        return new ResponseEntity<>(savedRezervacija, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/rezervacija/otkaziRezervaciju/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> otkaziRezervaciju(@PathVariable Long id){

        rezervacijaService.delete(id);

        return new ResponseEntity<>("Otkazana rezervacija!", HttpStatus.OK);
    }



    @RequestMapping(
            value = "/rezervacija/otkazi",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RezervacijaDTO> otkaziRez(@RequestBody RezervacijaDTO rezervacijaDTO) throws ParseException {

        String today = new SimpleDateFormat("yyyyMMdd HH:mm").format(new Date());
        int minutaDoPocetkaRezervacije = DateService.diffInMinutes(today, rezervacijaDTO.getProjekcija().getDatum(), rezervacijaDTO.getTermin().getVreme());

        //ne moze se otkazati rezervacija ako je manje od 30 minuta do pocetka
        if (minutaDoPocetkaRezervacije < 30) {
            RezervacijaDTO r = new RezervacijaDTO();
            r.setId(0l);
            //na frontu proveri da li je id nula, ako jeste onda jedan alert da ne moze da se otkaze
            return new ResponseEntity<>(r, HttpStatus.OK);
        }

        rezervacijaService.delete(rezervacijaDTO.getId());

        return new ResponseEntity<>(rezervacijaDTO, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/rezervacija/istorijaPoseta",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<RezervacijaDTO>> istorijaPoseta() throws ParseException {

        ArrayList<RezervacijaDTO> prethodneRezervacije = new ArrayList<>();

        String today = new SimpleDateFormat("yyyyMMdd HH:mm").format(new Date());

        Collection<Rezervacija> sveRezervacije = rezervacijaService.findByKorisnikId(KorisnikService.aktivanKorisnik.getId());
        for (Rezervacija r : sveRezervacije) {
            Teatar teatar = teatarService.findOne(r.getTeatarId());
            Projekcija projekcija = projekcijaService.findOne(r.getProjekcijaId());
            ProjekcijaDTO projekcijaDTO = new ProjekcijaDTO(projekcija.getId(), projekcija.getTeatarId(), projekcija.getFilm(), projekcija.getDatum(), projekcija.getTermini());
            Termin termin = terminService.findOne(r.getTermin().getId());
            Mesto mesto = mestoService.findOne(r.getMesto().getId());
            RezervacijaDTO rDTO = new RezervacijaDTO(r.getId(), teatar, projekcijaDTO, termin, mesto, KorisnikService.aktivanKorisnik.getId());

            int minutaDoPocetkaRezervacije = DateService.diffInMinutes(today, projekcija.getDatum(), termin.getVreme());

            if (minutaDoPocetkaRezervacije < 0) {
                prethodneRezervacije.add(rDTO);
            }

        }

        return new ResponseEntity<>(prethodneRezervacije, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/rezervacija/aktivneRezervacije",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<RezervacijaDTO>> aktivneRezervacije() throws ParseException {

        ArrayList<RezervacijaDTO> prethodneRezervacije = new ArrayList<>();

        String today = new SimpleDateFormat("yyyyMMdd HH:mm").format(new Date());

        Collection<Rezervacija> sveRezervacije = rezervacijaService.findByKorisnikId(KorisnikService.aktivanKorisnik.getId());
        for (Rezervacija r : sveRezervacije) {
            Teatar teatar = teatarService.findOne(r.getTeatarId());
            Projekcija projekcija = projekcijaService.findOne(r.getProjekcijaId());
            ProjekcijaDTO projekcijaDTO = new ProjekcijaDTO(projekcija.getId(), projekcija.getTeatarId(), projekcija.getFilm(), projekcija.getDatum(), projekcija.getTermini());
            Termin termin = terminService.findOne(r.getTermin().getId());
            Mesto mesto = mestoService.findOne(r.getMesto().getId());
            RezervacijaDTO rDTO = new RezervacijaDTO(r.getId(), teatar, projekcijaDTO, termin, mesto, KorisnikService.aktivanKorisnik.getId());

            int minutaDoPocetkaRezervacije = DateService.diffInMinutes(today, projekcija.getDatum(), termin.getVreme());

            if (minutaDoPocetkaRezervacije >= 0) {
                prethodneRezervacije.add(rDTO);
            }

        }

        return new ResponseEntity<>(prethodneRezervacije, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/film/save",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Film> saveFilm (@RequestBody Film f){

        Film film = filmService.save(f);

        return new ResponseEntity<>(film, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/film/get/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Film> getFilm (@PathVariable Long id){

        Film film = filmService.findOne(id);

        return new ResponseEntity<>(film, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/film/get/svi",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Film>> getFilmovi (){

        Collection<Film> film = filmService.findAll();

        return new ResponseEntity<>(film, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/projekcija/save",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Projekcija> saveProjekcija (@RequestBody Projekcija p) throws ParseException {

        if (p.getDatum().length() != 8) {
            p.setDatum(DateService.getFormattedDateUniversal(p.getDatum()));
        }
        Projekcija projekcija = projekcijaService.save(p);

        return new ResponseEntity<>(projekcija, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/projekcija/get/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Projekcija> getProjekcija (@PathVariable Long id){

        Projekcija projekcija = projekcijaService.findOne(id);
        //ProjekcijaDTO projekcijaDTO = new ProjekcijaDTO(projekcija.getId(), projekcija.getTeatarId(), projekcija.getFilm(), projekcija.getDatum(), projekcija.getTermini());

        return new ResponseEntity<>(projekcija, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/termin/save/{projekcijaId}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Termin> saveTermin (@RequestBody Termin t, @PathVariable Long projekcijaId){

        Projekcija projekcija = projekcijaService.findOne(projekcijaId);
        t.setProjekcija(projekcija);
        Sala sala = salaService.findOne(t.getSalaId());
        t.setSalaNaziv(sala.getNaziv());
        Termin termin = terminService.save(t);

        return new ResponseEntity<>(termin, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/termin/obrisi",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> deleteTermin (@RequestBody Termin t){

       terminService.delete(t);

       return new ResponseEntity<>(1, HttpStatus.OK);
    }


}
