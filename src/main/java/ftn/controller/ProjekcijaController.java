package ftn.controller;

import ftn.model.Korisnik;
import ftn.model.Projekcija;
import ftn.model.Teatar;
import ftn.service.ProjekcijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProjekcijaController {

    @Autowired
    private ProjekcijaService projekcijaService;

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

}
