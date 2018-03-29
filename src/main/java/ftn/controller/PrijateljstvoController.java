package ftn.controller;

import ftn.model.Korisnik;
import ftn.model.Prijateljstvo;
import ftn.service.PrijateljstvoService;
import ftn.service.TeatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PrijateljstvoController {

    @Autowired
    PrijateljstvoService prijateljstvoService;

    @RequestMapping(
            value = "/getFriendsList/{userId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Prijateljstvo>> listaPrijatelja(@PathVariable Long userId){

        //Long id = user.getId();
        System.out.println("Usla u friends kontroler");
        Collection<Prijateljstvo> listaPrijatelja= prijateljstvoService.listaPrijatelja(userId);

        System.out.println("aaass" + listaPrijatelja.size());

        return new ResponseEntity<Collection<Prijateljstvo>>(listaPrijatelja, HttpStatus.OK);
    }
}
