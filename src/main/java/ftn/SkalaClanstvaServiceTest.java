package ftn;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ftn.model.Projekcija;
import ftn.model.SkalaClanstva;
import ftn.service.SkalaClanstvaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SkalaClanstvaServiceTest {
	//id, bronzani, bronzani_popust, srebrni, srebrni_popust, zlatni, zlatni_popust, bodovi_za_posetu
	@Autowired
	SkalaClanstvaService skalaService;

	// listaKonstanti
	public static final int DB_COUNT = 1;
	
	public static final Long DB_ID = (long) 1;
	
	public static final Long id = (long) 2;
	public static final Integer bronzani = 3;
	public static final Integer bronzaniPopust = 5;
	public static final Integer srebrni =  5;
	public static final Integer srebrniPopust = 10;
	public static final Integer zlatni = 10;
	public static final Integer zlatniPopust = 20;
	public static final Integer bodoviZaPosetu = 5;
		

	@Test
	public void testFindOne() {
		SkalaClanstva t = skalaService.findOne(DB_ID);
		assertThat(t.getId()).isEqualTo(DB_ID);
		assertThat(t.getSrebrni()).isEqualTo(50);
		assertThat(t.getZlatni()).isEqualTo(150);
	}

	@Test
	public void testGetAll() {
		List<SkalaClanstva> skale = skalaService.findAll();
		assertThat(skale.size()).isEqualTo(DB_COUNT);

	}



}
