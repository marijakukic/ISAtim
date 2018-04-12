package ftn.controller;


import ftn.model.Korisnik;
import ftn.model.MailSending;
import ftn.service.PrijateljstvoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ftn.service.KorisnikService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private PrijateljstvoService prijateljstvoService;


    @RequestMapping(
            value = "/setActiveUser/{userId}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Korisnik> setActiveUser(@PathVariable Long userId){
        Korisnik aktivanKorisnik = korisnikService.findUserDetails(userId);
        KorisnikService.aktivanKorisnik = aktivanKorisnik;
        return new ResponseEntity<>(aktivanKorisnik, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getActiveUser",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Korisnik> getActiveUser(){
        return new ResponseEntity<>(KorisnikService.aktivanKorisnik, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/registration",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> registration (@RequestBody Korisnik korisnikRequest) throws MessagingException {
        Korisnik korisnik = korisnikRequest;
        korisnikService.save(korisnik);

        MailSending.sendMail("boxboux@gmail.com", "Aktivacija", "http://localhost:9000/activate/"+korisnik.getEmail());

        return new ResponseEntity<Korisnik>(korisnik,HttpStatus.OK);
    }

    @RequestMapping(
            value = "/editUser",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> editUser(@RequestBody Korisnik korisnikRequest) {

        Korisnik korisnik = korisnikService.save(korisnikRequest);
        KorisnikService.aktivanKorisnik = korisnik;
        return new ResponseEntity<>(korisnik,HttpStatus.OK);
    }

    @RequestMapping(
            value = "/activate/{email:.+}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> emailActivationKorisnika(@PathVariable String email){
        Integer i = korisnikService.setActivated(true, email);
        String s = "Nalog je uspesno kreiran!";
        return new ResponseEntity<String>(s, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/login/{email:.+}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> login(@PathVariable String email){

        System.out.println("Usla u login kontroler");
        Korisnik korisnik = korisnikService.findByEmail(email);


        System.out.println("Aaaaaaaaaa" + korisnik.email + korisnik.lozinka);

        return new ResponseEntity<Korisnik>(korisnik,HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getUserDetails/{userId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> getUserDetails(@PathVariable Long userId){

        //Long id = user.getId();
        System.out.println("Usla u userDetails kontroler");
        Korisnik korisnik = korisnikService.findUserDetails(userId);


        System.out.println("Aaaaaaaaaa" + korisnik.email + korisnik.lozinka);

        return new ResponseEntity<Korisnik>(korisnik,HttpStatus.OK);
    }


    @RequestMapping(
            value = "/getAllUsersExceptMe/{userId}/{ime}/{prezime}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Korisnik>> getAllUsersExceptMe(@PathVariable Long userId, @PathVariable String ime, @PathVariable String prezime){

        ArrayList<Korisnik> korisnici = new ArrayList<>();
        if ("undefined".equals(ime)) {
            ime = "";
        }
        if ("undefined".equals(prezime)) {
            prezime = "";
        }

        if (!StringUtils.isEmpty(ime) && !StringUtils.isEmpty(prezime)) {
            korisnici = (ArrayList<Korisnik>) korisnikService.getAllUsersExceptMeByNameAndSurname(userId, ime, prezime);
        }
        else if (!StringUtils.isEmpty(ime) && StringUtils.isEmpty(prezime)) {
            korisnici = (ArrayList<Korisnik>) korisnikService.getAllUsersExceptMeByName(userId, ime);
        }
        else if (StringUtils.isEmpty(ime) && !StringUtils.isEmpty(prezime)) {
            korisnici = (ArrayList<Korisnik>) korisnikService.getAllUsersExceptMeBySurname(userId, prezime);
        }
        else {
            korisnici = (ArrayList<Korisnik>) korisnikService.getAllUsersExceptMe(userId);
        }

        ArrayList<Korisnik> moguciKorisnici = new ArrayList<>();
        for (Korisnik korisnik : korisnici) {

            if (prijateljstvoService.vecPrijatelji(userId, korisnik.getId()).size() == 1) {
               continue;
            }
            else {
                moguciKorisnici.add(korisnik);
            }
        }

        return new ResponseEntity<>(moguciKorisnici,HttpStatus.OK);
    }





}
