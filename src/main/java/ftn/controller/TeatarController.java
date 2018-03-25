package ftn.controller;

import ftn.model.Korisnik;
import ftn.model.Teatar;
import ftn.service.TeatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TeatarController {

    @Autowired
    TeatarService teatarService;

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
}
