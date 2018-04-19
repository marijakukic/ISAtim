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

import ftn.model.Mesto;
import ftn.model.Sala;
import ftn.service.SalaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SalaTestService {

	@Autowired
	SalaService salaService;

	// listaKonstanti
	public static final int DB_COUNT = 2;
	
	public static final Long DB_ID = (long) 1;
	public static final int brmesta =  4;

	public static final Long id = (long) 3;
	public static final String naziv = "Sala 3";
	public static final Long idTeater = (long) 1;


	@Test
	public void testFindOne() {
		Sala t = salaService.findOne(DB_ID);
		assertThat(t.getId()).isEqualTo(DB_ID);
		assertThat(t.getNaziv()).isEqualTo("Sala 1");
	}

	@Test
	public void testGetAll() {
		List<Sala> teatri = salaService.findAll();
		assertThat(teatri.size()).isEqualTo(DB_COUNT);

	}

	@Test
	public void testGetAllSeats() {
		Collection<Mesto> mestaUSali = salaService.getAllSalaSeats(DB_ID);
		assertThat(mestaUSali.size()).isEqualTo(brmesta);

	}

	/*@Test
	@Transactional
	@Rollback(true)
	public void testAdd() {
		Sala t = new Sala();
		t.setId(id);
		t.setNaziv(naziv);
		t.setTeatarId(idTeater);

		int dbSizeBeforeAdd = salaService.findAll().size();

		salaService.save(t);

		List<Sala> teatri = salaService.findAll();
		assertThat(teatri).hasSize(dbSizeBeforeAdd + 1);
		Sala dbKor = teatri.get(teatri.size() - 1);
		assertThat(dbKor.getId()).isEqualTo(id);
		assertThat(dbKor.getNaziv()).isEqualTo(naziv);
		assertThat(dbKor.getTeatarId()).isEqualTo(idTeater);

	}*/

	@Test
	public void testfindByTeatarId() {
		Collection<Sala> sale = salaService.findByTeatarId(idTeater);
		assertThat(sale.size()).isEqualTo(2);


	}
}
