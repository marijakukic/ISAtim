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
    public ResponseEntity<Collection<Projekcija>> getProjectionsForDate(@PathVariable Long teatarId,@PathVariable String date){

        Collection<Projekcija> projekcije = projekcijaService.findByTeatarIdAndDatum(teatarId, date);

        return new ResponseEntity<>(projekcije, HttpStatus.OK);
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


}
