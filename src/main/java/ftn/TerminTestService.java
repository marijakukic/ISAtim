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

import ftn.model.Projekcija;
import ftn.model.Termin;
import ftn.service.ProjekcijaService;
import ftn.service.TerminService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TerminTestService {
	
	@Autowired
	TerminService terminService;
	
	@Autowired
	ProjekcijaService projekcijaService;

	// listaKonstanti
	public static final int DB_COUNT = 2;
	
	public static final Long DB_ID = (long) 1;

	public static final Long idTermina = (long) 3;
	public static final Double cena = (double) 300;
	public static final Long idSala = (long) 1;
	public static final String salaNaziv = "Sala 1";
	public static final String vreme =  "15:30";
	public static final Long projekcijaId = (long)1;


	@Test
	public void testFindOne() {
		Termin t = terminService.findOne(DB_ID);
		assertThat(t.getId()).isEqualTo(DB_ID);
		assertThat(t.getCena()).isEqualTo(300);
		assertThat(t.getSalaId()).isEqualTo(1);
		assertThat(t.getSalaNaziv()).isEqualTo("Sala 1");
		assertThat(t.getVreme()).isEqualTo("14:30");
		assertThat(t.getProjekcija().getId()).isEqualTo(1);
		
	}

	@Test
	public void testGetAll() {
		List<Termin> termini = terminService.findAll();
		assertThat(termini.size()).isEqualTo(DB_COUNT);

	}

	@Test
	@Transactional
	@Rollback(true)
	public void testAdd() {
		Termin t = new Termin();
		t.setId(idTermina);
		t.setCena(cena);
		t.setSalaId(idSala);
		Projekcija p = new Projekcija();
		p = projekcijaService.findOne(projekcijaId);
		t.setProjekcija(p);
		t.setSalaNaziv(salaNaziv);

		int dbSizeBeforeAdd = terminService.findAll().size();

		terminService.save(t);

		List<Termin> teatri = terminService.findAll();
		assertThat(teatri).hasSize(dbSizeBeforeAdd + 1);
		Termin dbKor = teatri.get(teatri.size() - 1);
		assertThat(dbKor.getId()).isEqualTo(idTermina);
		assertThat(dbKor.getCena()).isEqualTo(cena);
		assertThat(dbKor.getSalaId()).isEqualTo(idSala);
		assertThat(dbKor.getProjekcija().getId()).isEqualTo(projekcijaId);
		assertThat(dbKor.getSalaNaziv()).isEqualTo(salaNaziv);

	}

	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteId() {
	
		
		int dbSizeBeforeAdd = terminService.findAll().size();
		assertThat(dbSizeBeforeAdd).isEqualTo(2);
		Termin m = terminService.findOne(1l);
		
		terminService.delete(1l);
		
		Termin mPosle = terminService.findOne(1l);
		assertThat(mPosle).isNull();
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteTermin() {
	
		
		int dbSizeBeforeAdd = terminService.findAll().size();
		assertThat(dbSizeBeforeAdd).isEqualTo(2);
		Termin m = terminService.findOne(1l);
		
		terminService.delete(m);
		
		Termin mPosle = terminService.findOne(m.getId());
		assertThat(mPosle).isNull();
		

	}
	
	@Test
	public void findByProjekcija(){
		Projekcija p = projekcijaService.findOne(1l);
		Collection<Termin> kolekcija = terminService.findByProjekcija(p);
		assertThat(kolekcija.size()).isEqualTo(2);
	}
}
