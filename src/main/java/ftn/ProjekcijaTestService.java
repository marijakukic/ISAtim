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

import ftn.model.Film;
import ftn.model.Projekcija;
import ftn.service.FilmService;
import ftn.service.ProjekcijaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjekcijaTestService {
	
	@Autowired
	ProjekcijaService projekcijaService;
	
	@Autowired
	FilmService filmService;

	// listaKonstanti
	public static final int DB_COUNT = 2;
	
	public static final Long DB_ID = (long) 1;

	public static final Long idProjekcije = (long) 3;
	public static final String datum = "18020411";
	public static final Long idTeatar = (long) 1;
	public static final Long idFilm = (long) 1;
	

	@Test
	public void testFindOne() {
		Projekcija t = projekcijaService.findOne(DB_ID);
		assertThat(t.getId()).isEqualTo(DB_ID);
		assertThat(t.getTeatarId()).isEqualTo(1l);
		assertThat(t.getFilm().getId()).isEqualTo(1l);
	}

	@Test
	public void testGetAll() {
		List<Projekcija> teatri = projekcijaService.findAll();
		assertThat(teatri.size()).isEqualTo(DB_COUNT);

	}

	@Test
	@Transactional
	@Rollback(true)
	public void testAdd() {
		Projekcija t = new Projekcija();
		t.setId(idProjekcije);
		t.setDatum(datum);
		Film f = filmService.findOne(idFilm);
		t.setFilm(f);
		t.setTeatarId(idTeatar);

		int dbSizeBeforeAdd = projekcijaService.findAll().size();

		projekcijaService.save(t);

		List<Projekcija> teatri = projekcijaService.findAll();
		assertThat(teatri).hasSize(dbSizeBeforeAdd + 1);
		Projekcija dbKor = teatri.get(teatri.size() - 1);
		assertThat(dbKor.getId()).isEqualTo(idProjekcije);
		assertThat(dbKor.getDatum()).isEqualTo(datum);
		assertThat(dbKor.getFilm().getId()).isEqualTo(f.getId());
		assertThat(dbKor.getTeatarId()).isEqualTo(idTeatar);
		
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testDelete() {
	
		
		int dbSizeBeforeAdd = projekcijaService.findAll().size();
		assertThat(dbSizeBeforeAdd).isEqualTo(2);
		Projekcija m = projekcijaService.findOne(1l);
		
		projekcijaService.delete(m);
		
		Projekcija mPosle = projekcijaService.findOne(m.getId());
		assertThat(mPosle).isNull();
		

	}
	
	@Test
	public void testfindByTeatarIdAndDatum(){
		Collection<Projekcija> projekcije = projekcijaService.findByTeatarIdAndDatumGreaterThanEqual(1l, "18020411");
		assertThat(projekcije.size()).isEqualTo(2);
	}
	
	@Test
	public void testfindByTeatarIdAndDatumGrater(){
		Collection<Projekcija> projekcije = projekcijaService.findByTeatarIdAndDatumGreaterThanEqual(1l, "18020411");
		assertThat(projekcije.size()).isEqualTo(2);
	}

}
