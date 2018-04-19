package ftn.controller;

import ftn.dto.PrijateljstvoDTO;
import ftn.model.Korisnik;
import ftn.model.Prijateljstvo;
import ftn.service.KorisnikService;
import ftn.service.PrijateljstvoService;
import ftn.service.TeatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PrijateljstvoController {

    @Autowired
    private PrijateljstvoService prijateljstvoService;

    @Autowired
    private KorisnikService korisnikService;

    @RequestMapping(
            value = "/getFriendsList/{userId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<PrijateljstvoDTO>> listaPrijatelja(@PathVariable Long userId){

        ArrayList<Prijateljstvo> listaPrijatelja = (ArrayList<Prijateljstvo>) prijateljstvoService.listaPrijatelja(userId);
        ArrayList<PrijateljstvoDTO> listaPrijateljaDTO = new ArrayList<PrijateljstvoDTO>();
        for (Prijateljstvo prijateljstvo : listaPrijatelja) {
            Korisnik receiver = korisnikService.findUserDetails(prijateljstvo.getIdKorisnik2());
            PrijateljstvoDTO prijateljstvoDTO = new PrijateljstvoDTO(receiver, prijateljstvo.getPrijatelji(), prijateljstvo.getZahtevPoslao());
            listaPrijateljaDTO.add(prijateljstvoDTO);
        }

        return new ResponseEntity<>(listaPrijateljaDTO, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/sendFriendRequest/{sender}/{receiver}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Prijateljstvo> listaPrijatelja(@PathVariable Long sender, @PathVariable Long receiver){

        Prijateljstvo prijateljstvo1 = new Prijateljstvo(sender, receiver, false, sender, true);
        Prijateljstvo prijateljstvo2 = new Prijateljstvo(receiver, sender, false, sender, true);

        prijateljstvoService.save(prijateljstvo1);
        prijateljstvoService.save(prijateljstvo2);

        return new ResponseEntity<>(prijateljstvo1, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/acceptOrRefuseFriendRequest/{sender}/{receiver}/{decision}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Prijateljstvo> acceptOrRefuseFriendRequest(@PathVariable Long sender, @PathVariable Long receiver, @PathVariable Boolean decision){

        Prijateljstvo prijateljstvo1 = prijateljstvoService.findBySenderAndRecieverIds(sender, receiver);
        Prijateljstvo prijateljstvo2 = prijateljstvoService.findBySenderAndRecieverIds(receiver, sender);

        if (decision == true) {
            prijateljstvo1.setPrijatelji(true);
            prijateljstvoService.save(prijateljstvo1);
            prijateljstvo2.setPrijatelji(true);
            prijateljstvoService.save(prijateljstvo2);
        }
        else {
            prijateljstvoService.delete(prijateljstvo1);
            prijateljstvoService.delete(prijateljstvo2);
        }

        return new ResponseEntity<>(prijateljstvo1, HttpStatus.OK);
    }



}
