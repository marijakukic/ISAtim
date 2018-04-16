package ftn.controller;

import ftn.dto.PonudaDTO;
import ftn.model.*;
import ftn.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Autowired
    private KorisnikService korisnikService;

    @RequestMapping(
            value    = "/fanZona/getAllRekvizite/{teatarId}/{stanje}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<Rekvizit>> getAllRekvizite(@PathVariable Long teatarId, @PathVariable String stanje) {
        Collection<Rekvizit> rekviziti = null;
        Long korisnikId = KorisnikService.aktivanKorisnik.getId();
        if ("polovan".equals(stanje)) {
            rekviziti = rekvizitService.findByTeatarIdAndStanjeAndKorisnikIdNotAndOdobren(teatarId, stanje, korisnikId, true);
        }
        else {
            rekviziti = rekvizitService.findByTeatarIdAndStanje(teatarId, stanje);
        }

        return new ResponseEntity<>(rekviziti, HttpStatus.OK);
    }

    @RequestMapping(
            value    = "/fanZona/getAllRekviziteZaOdobravanje/{teatarId}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<Rekvizit>> getAllRekviziteZaOdobravanje(@PathVariable Long teatarId) {

        Long korisnikId = KorisnikService.aktivanKorisnik.getId();

        Collection<Rekvizit> rekviziti = rekvizitService.findByTeatarIdAndStanjeAndOdobren(teatarId, "polovan", false);

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
            value    = "/ponuda/findPonudeByRekvizit/{rekvizitId}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ArrayList<PonudaDTO>> findPonudeByRekvizit(@PathVariable Long rekvizitId) {

        ArrayList<PonudaDTO> ponudeDTO = new ArrayList<>();
        Collection<Ponuda> ponude = ponudaService.findByRekvizitId(rekvizitId);
        for (Ponuda ponuda : ponude) {
            Korisnik korisnik = korisnikService.findUserDetails(ponuda.getKorisnikId());
            PonudaDTO ponudaDTO = new PonudaDTO(ponuda.getId(), ponuda.getTeatarId(), korisnik,ponuda.getRekvizitId(), ponuda.getCena(), ponuda.getPoslata(), ponuda.getOdabrana());
            ponudeDTO.add(ponudaDTO);
        }

        return new ResponseEntity<>(ponudeDTO, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/ponuda/odaberi",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ponuda> saveKupovina(@RequestBody PonudaDTO pon) throws MessagingException {

        //pon.setOdabrana(true);
        Ponuda pnd = new Ponuda();
        pnd.setId(pon.getId());
        pnd.setOdabrana(true);
        pnd.setCena(pon.getCena());
        pnd.setKorisnikId(pon.getKorisnik().getId());
        pnd.setRekvizitId(pon.getRekvizitId());
        pnd.setTeatarId(pon.getTeatarId());

        Ponuda ponuda = ponudaService.save(pnd);

        Korisnik korisnik = korisnikService.findUserDetails(ponuda.getKorisnikId());
        MailSending.sendMail("boxboux@gmail.com", "Ponuda", "Postovani, Vasa ponuda je prihvacena! Cestitamo!");

        Collection<Ponuda> ponude = ponudaService.findByRekvizitIdAndIdNot(ponuda.getRekvizitId(), ponuda.getId());
        for (Ponuda p : ponude) {
            p.setOdabrana(false);
            Ponuda ppp = ponudaService.save(p);
            Korisnik k = korisnikService.findUserDetails(ppp.getKorisnikId());
            MailSending.sendMail("boxboux@gmail.com", "Ponuda", "Postovani, Vasa ponuda nazalost nije prihvacena! :(");
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
    public ResponseEntity<Rekvizit> saveRekvizit(@RequestBody Rekvizit rek){

        Rekvizit rekvizit = rekvizitService.save(rek);
        return new ResponseEntity<>(rekvizit, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/rekvizit/saveOglas",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rekvizit> saveOglas(@RequestBody Rekvizit rek) throws ParseException {

        rek.setDatum(DateService.getFormattedDateUniversal(rek.getDatum()));
        Rekvizit rekvizit = rekvizitService.save(rek);
        return new ResponseEntity<>(rekvizit, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/rekvizit/odobri",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rekvizit> odobri(@RequestBody Rekvizit rek){

        rek.setOdobren(true);
        Rekvizit rekvizit = rekvizitService.save(rek);
        return new ResponseEntity<>(rekvizit, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/rekvizit/ponisti",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> ponisti(@RequestBody Rekvizit rek){

        rekvizitService.delete(rek);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

}
