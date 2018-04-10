package ftn.controller;

import com.gs.collections.impl.map.mutable.primitive.SynchronizedShortObjectMap;
import ftn.model.*;
import ftn.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TeatarController {

    @Autowired
    private TeatarService teatarService;

    @Autowired
    private SalaService salaService;

    @Autowired
    private FilmService filmService;

    @Autowired
    private ProjekcijaService projekcijaService;

    @Autowired
    private SegmentService segmentService;

    @Autowired
    private MestoService mestoService;

    @RequestMapping(
            value = "/registrationTeatar",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Teatar> registration (@RequestBody Teatar teatarRequest){

        Teatar teatar= teatarRequest;
        teatarService.save(teatar);

        System.out.println("Usla u kontroler");

        return new ResponseEntity<Teatar>(HttpStatus.OK);
    }

    @RequestMapping(
        value    = "/bioskop/sviBioskopi",
        method   = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity<Collection<Teatar>> sviBioskopi() {
        String tip = "bioskop";
        Collection<Teatar> bioskopi = teatarService.getAllBioskop(tip);
        System.out.println("Bioskop" + bioskopi.size());
        return new ResponseEntity<Collection<Teatar>>(bioskopi, HttpStatus.OK);
    }

    @RequestMapping(
            value    = "/pozorista/svaPozorista",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity<Collection<Teatar>> svaPozorista() {
        String tip = "pozoriste";
        Collection<Teatar> pozorista = teatarService.getAllBioskop(tip);
        return new ResponseEntity<Collection<Teatar>>(pozorista, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/sala/saveSala",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sala> registration (@RequestBody Sala sala){

        Sala sala1 = salaService.save(sala);

        return new ResponseEntity<Sala>(sala1, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/film/saveFilm",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Film> registration (@RequestBody Film film){

        Film film1 = filmService.save(film);

        return new ResponseEntity<Film>(film1, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/projekcija/saveProjekcija",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Projekcija> registration (@RequestBody Projekcija projekcija){

        Projekcija projekcija1 = projekcijaService.save(projekcija);

        return new ResponseEntity<Projekcija>(projekcija1, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/segment/saveSegment",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Segment> registration (@RequestBody Segment segment){

        Segment segment1 = segmentService.save(segment);

        return new ResponseEntity<Segment>(segment1, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/segment/getAllSalaSegments/{salaId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Segment>> getAllSalaSegments (@PathVariable Long salaId){

        Collection<Segment> segments = segmentService.findBySalaId(salaId);

        return new ResponseEntity<>(segments, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/mesto/saveMesto",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mesto> registration (@RequestBody Mesto mesto){

        Mesto mesto1 = mestoService.save(mesto);

        return new ResponseEntity<Mesto>(mesto1, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/mesto/deleteMesto/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteMesto (@PathVariable Long id){

        mestoService.deleteMesto(id);

        return new ResponseEntity<>("Uspesno obrisano mesto", HttpStatus.OK);
    }







}
