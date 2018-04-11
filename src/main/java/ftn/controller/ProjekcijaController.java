package ftn.controller;

import ftn.dto.MestoDTO;
import ftn.dto.ProjekcijaDTO;
import ftn.model.*;
import ftn.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
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
        Mesto mesto = mestoService.fincOne(mestoId);
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


}
