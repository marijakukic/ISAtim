package ftn.controller;


import ftn.model.Korisnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ftn.service.KorisnikService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class KorisnikController {

    @Autowired
    KorisnikService korisnikService;

    @RequestMapping(
            value = "/registration",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> registration (@RequestBody Korisnik korisnikRequest){

        Korisnik korisnik = korisnikRequest;
        korisnikService.save(korisnik);

        System.out.println("Usla u kontroler");

        return new ResponseEntity<Korisnik>(HttpStatus.OK);
    }

    @RequestMapping(
            value = "/login/{email}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> login(@PathVariable String email){

        System.out.println("Usla u login kontroler");
        Korisnik korisnik = korisnikService.findByEmail(email);


        System.out.println("Aaaaaaaaaa" + korisnik.email + korisnik.lozinka);

        return new ResponseEntity<Korisnik>(korisnik,HttpStatus.OK);
    }
}
