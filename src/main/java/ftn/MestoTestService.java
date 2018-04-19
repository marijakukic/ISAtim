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
import ftn.model.Segment;
import ftn.service.MestoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MestoTestService {
	
	@Autowired
	MestoService mestoService;

	// listaKonstanti
	public static final int DB_COUNT = 4;
	
	public static final Long DB_ID = (long) 1;

	public static final Long idMesta = (long) 5;
	public static final String naziv = "5";
	public static final Long idSala = (long) 1;
	public static final Long idSegment = (long) 1;
	public static final Integer x =  50;
	public static final Integer y = 150;


	@Test
	public void testFindOne() {
		Mesto t = mestoService.findOne(DB_ID);
		assertThat(t.getId()).isEqualTo(DB_ID);
		assertThat(t.getNaziv()).isEqualTo("1");
	}

	@Test
	public void testGetAll() {
		List<Mesto> teatri = mestoService.findAll();
		assertThat(teatri.size()).isEqualTo(DB_COUNT);

	}

	@Test
	@Transactional
	@Rollback(true)
	public void testAdd() {
		Mesto t = new Mesto();
		t.setId(5l);
		t.setNaziv("5");
		t.setSalaId(1l);
		t.setSegmentId(1l);
		t.setX(50);
		t.setY(150);

		int dbSizeBeforeAdd = mestoService.findAll().size();

		mestoService.save(t);

		List<Mesto> teatri = mestoService.findAll();
		assertThat(teatri).hasSize(dbSizeBeforeAdd + 1);
		Mesto dbKor = teatri.get(teatri.size() - 1);
		assertThat(dbKor.getId()).isEqualTo(idMesta);
		assertThat(dbKor.getNaziv()).isEqualTo(naziv);
		assertThat(dbKor.getSalaId()).isEqualTo(idSala);
		assertThat(dbKor.getSegmentId()).isEqualTo(idSegment);
		assertThat(dbKor.getX()).isEqualTo(x);
		assertThat(dbKor.getY()).isEqualTo(y);

	}

	@Test
	@Transactional
	@Rollback(true)
	public void testDelete() {
	
		
		int dbSizeBeforeAdd = mestoService.findAll().size();
		assertThat(dbSizeBeforeAdd).isEqualTo(4);
		Mesto m = mestoService.findOne(4l);
		assertThat(m.getNaziv()).isEqualTo("4");
		mestoService.deleteMesto(4l);
		
		Mesto mPosle = mestoService.findOne(4l);
		assertThat(mPosle).isNull();
		

	}


}
