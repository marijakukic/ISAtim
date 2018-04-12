package ftn.controller;

import ftn.model.Ponuda;
import ftn.model.Rekvizit;
import ftn.service.PonudaService;
import ftn.service.RekvizitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FanZonaController {


    @Autowired
    private RekvizitService rekvizitService;

    @Autowired
    private PonudaService ponudaService;

    @RequestMapping(
            value    = "/fanZona/getAllRekvizite/{teatarId}/{stanje}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<Rekvizit>> getAllRekvizite(@PathVariable Long teatarId, @PathVariable String stanje) {

        Collection<Rekvizit> rekviziti = rekvizitService.findByTeatarIdAndStanje(teatarId, stanje);

        return new ResponseEntity<>(rekviziti, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/ponuda/savePonuda",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ponuda> registration (@RequestBody Ponuda pon){

        Ponuda ponuda = ponudaService.save(pon);

        return new ResponseEntity<>(ponuda, HttpStatus.OK);
    }

}
