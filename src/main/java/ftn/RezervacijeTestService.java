package ftn;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ftn.model.Rezervacija;
import ftn.model.Termin;
import ftn.service.RezervacijaService;
import ftn.service.TerminService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RezervacijeTestService {
	
	@Autowired
	RezervacijaService rezervacijaService;
	
	@Autowired
	TerminService terminService;
	
	public static final int DB_COUNT = 3;
	
	public static final Long DB_ID = (long) 1;

	public static final Long id = (long) 4;
	public static final Long korisnik = (long)2;
	public static final Long projekcija = (long) 1;
	public static final Long teatar = (long) 1;

//id, korisnik_id, projekcija_id, teatar_id, mesto_id, termin_id

	@Test
	public void testFindOne() {
		Rezervacija t = rezervacijaService.findOne(DB_ID);
		assertThat(t.getId()).isEqualTo(DB_ID);
		assertThat(t.getKorisnikId()).isEqualTo(3);
		assertThat(t.getProjekcijaId()).isEqualTo(1);
		assertThat(t.getTeatarId()).isEqualTo(1);
		assertThat(t.getMesto().getId()).isEqualTo(4);
		
	}

	@Test
	public void testGetAll() {
		List<Rezervacija> teatri = rezervacijaService.findAll();
		assertThat(teatri.size()).isEqualTo(DB_COUNT);

	}

	@Test
	@Transactional
	@Rollback(true)
	public void testAdd() {
		Rezervacija t = new Rezervacija();
		t.setId(id);
		t.setKorisnikId(korisnik);
		t.setProjekcijaId(projekcija);
		t.setTeatarId(teatar);

		int dbSizeBeforeAdd = rezervacijaService.findAll().size();

		rezervacijaService.save(t);

		List<Rezervacija> teatri = rezervacijaService.findAll();
		assertThat(teatri).hasSize(dbSizeBeforeAdd + 1);
		Rezervacija dbKor = teatri.get(teatri.size() - 1);
		assertThat(dbKor.getId()).isEqualTo(id);
		assertThat(dbKor.getKorisnikId()).isEqualTo(korisnik);
		assertThat(dbKor.getProjekcijaId()).isEqualTo(projekcija);
		assertThat(dbKor.getTeatarId()).isEqualTo(teatar);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testDelete() {
	
		
		int dbSizeBeforeAdd = rezervacijaService.findAll().size();
		assertThat(dbSizeBeforeAdd).isEqualTo(3);
		Rezervacija m = rezervacijaService.findOne(1l);
		rezervacijaService.delete(1l);
		
		Rezervacija mPosle = rezervacijaService.findOne(1l);
		assertThat(mPosle).isNull();
		

	}
	
	@Test
	public void testFindByTermin(){
		Termin t = new Termin();
		t = terminService.findOne(1l);
		Collection<Rezervacija> rezervacije = rezervacijaService.findByTermin(t);
		assertThat(rezervacije.size()).isEqualTo(3);
	}
	
	@Test
	public void testFindByKorisnikId(){
		Collection<Rezervacija> rezervacije = rezervacijaService.findByKorisnikId(2l);
		assertThat(rezervacije.size()).isEqualTo(2);
	}
	
	@Test
	public void findByKorisnikIdIsNull(){
		Collection<Rezervacija> rezervacije = rezervacijaService.findByKorisnikIdIsNull();
		assertThat(rezervacije.size()).isEqualTo(0);
	}
	
	@Test
	public void findByProjekcijaId(){
		Collection<Rezervacija> rezervacije = rezervacijaService.findByProjekcijaId(1l);
		assertThat(rezervacije.size()).isEqualTo(3);
	}
	
	


}
