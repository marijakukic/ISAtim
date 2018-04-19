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
import ftn.service.SegmentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SegmentTestService {
	
	@Autowired
	SegmentService SegmentService;

	// listaKonstanti
	public static final int DB_COUNT = 2;
	
	public static final Long DB_ID = (long) 1;

	public static final Long id = (long) 3;
	public static final String boja = "#ff0";
	public static final String naziv = "Balkon";
	public static final Long idSala = (long) 1;


	@Test
	public void testFindOne() {
		Segment t = SegmentService.findOne(DB_ID);
		assertThat(t.getId()).isEqualTo(DB_ID);
		assertThat(t.getNaziv()).isEqualTo("Classic");
	}

	@Test
	public void testGetAll() {
		List<Segment> teatri = SegmentService.findAll();
		assertThat(teatri.size()).isEqualTo(DB_COUNT);

	}

	@Test
	@Transactional
	@Rollback(true)
	public void testAdd() {
		Segment t = new Segment();
		t.setId(3l);
		t.setNaziv("Balkon");
		t.setSalaId(1l);

		int dbSizeBeforeAdd = SegmentService.findAll().size();

		SegmentService.save(t);

		List<Segment> teatri = SegmentService.findAll();
		assertThat(teatri).hasSize(dbSizeBeforeAdd + 1);
		Segment dbKor = teatri.get(teatri.size() - 1);
		assertThat(dbKor.getId()).isEqualTo(id);
		assertThat(dbKor.getNaziv()).isEqualTo(naziv);
		assertThat(dbKor.getSalaId()).isEqualTo(idSala);

	}

	@Test
	public void testfinBySalaId() {
		Collection<Segment> sale = SegmentService.findBySalaId(idSala);
		assertThat(sale.size()).isEqualTo(2);


	}

}
