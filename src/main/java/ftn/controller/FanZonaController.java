package ftn.controller;

import ftn.model.Kupovina;
import ftn.model.Ponuda;
import ftn.model.Rekvizit;
import ftn.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FanZonaController {


    @Autowired
    private RekvizitService rekvizitService;

    @Autowired
    private PonudaService ponudaService;

    @Autowired
    private KupovinaService kupovinaService;

    @RequestMapping(
            value    = "/fanZona/getAllRekvizite/{teatarId}/{stanje}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<Rekvizit>> getAllRekvizite(@PathVariable Long teatarId, @PathVariable String stanje) {
        Collection<Rekvizit> rekviziti = null;
        Long korisnikId = KorisnikService.aktivanKorisnik.getId();
        if ("polovan".equals(stanje)) {
            rekviziti = rekvizitService.findByTeatarIdAndStanjeAndKorisnikIdNot(teatarId, stanje, korisnikId);
        }
        else {
            rekviziti = rekvizitService.findByTeatarIdAndStanje(teatarId, stanje);
        }

        return new ResponseEntity<>(rekviziti, HttpStatus.OK);
    }

    @RequestMapping(
            value    = "/fanZona/getAllMojeRekvizite/{teatarId}/{stanje}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<Rekvizit>> getAllMojeRekvizite(@PathVariable Long teatarId, @PathVariable String stanje) {
        Long korisnikId = KorisnikService.aktivanKorisnik.getId();

        Collection<Rekvizit> rekviziti = rekvizitService.findByTeatarIdAndStanjeAndKorisnikId(teatarId, stanje, korisnikId);

        return new ResponseEntity<>(rekviziti, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/ponuda/savePonuda",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ponuda> savePonuda(@RequestBody Ponuda pon){

        Ponuda postojecaPonuda = ponudaService.findByKorisnikIdAndRekvizitId(pon.getKorisnikId(), pon.getRekvizitId());

        if (postojecaPonuda!=null) {
            postojecaPonuda.setCena(pon.getCena());
            postojecaPonuda = ponudaService.save(postojecaPonuda);
            return new ResponseEntity<>(postojecaPonuda, HttpStatus.OK);
        }

        Ponuda ponuda = ponudaService.save(pon);
        return new ResponseEntity<>(ponuda, HttpStatus.OK);
    }

    @RequestMapping(
            value    = "/ponuda/findPonuda/{korisnikId}/{rekvizitId}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Ponuda> findPonuda(@PathVariable Long korisnikId, @PathVariable Long rekvizitId) {

        Ponuda ponuda = ponudaService.findByKorisnikIdAndRekvizitId(korisnikId, rekvizitId);
        if (ponuda == null) {
            Ponuda nemaPonude = new Ponuda();
            nemaPonude.setCena(0.0);
            return new ResponseEntity<>(nemaPonude, HttpStatus.OK);
        }

        return new ResponseEntity<>(ponuda, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/kupovina/saveKupovina",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Kupovina> saveKupovina(@RequestBody Kupovina kup){

        String datum = new SimpleDateFormat("yyyyMMdd").format(new Date());
        kup.setDatum(datum);

        Kupovina kupovina = kupovinaService.save(kup);
        return new ResponseEntity<>(kupovina, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/rekvizit/saveRekvizit",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rekvizit> saveRekvizit(@RequestBody Rekvizit rek) throws ParseException {

        rek.setDatum(DateService.getFormattedDateUniversal(rek.getDatum()));
        Rekvizit rekvizit = rekvizitService.save(rek);
        return new ResponseEntity<>(rekvizit, HttpStatus.OK);
    }

}
