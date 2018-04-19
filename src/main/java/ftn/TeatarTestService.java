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

import ftn.model.Teatar;
import ftn.service.TeatarService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TeatarTestService {
	
	@Autowired
	TeatarService teatarService;
	
	//listaKonstanti
	public static final int DB_COUNT = 5;
	public static final int DB_BISOKOPI = 2;
	public static final Long DB_ID = (long) 1;
	public static final Long DB_ID1 = (long) 2;
	
	public static final Long id = (long)6;
	public static final String naziv ="NoviBioskop" ;
	public static final String adresa = "Gogoljeva 15";
	public static final String promotivni ="prvi u trendu" ;
	
	public static final String nazivBioskopa ="Arena Cineplex" ;
	public static final String nazivTeatra ="Pozoriste" ;

	
	@Test
	public void testFindOne() {
		Teatar t = teatarService.findOne(DB_ID);
		assertThat(t.getId()).isEqualTo(DB_ID);
		assertThat(t.getNaziv()).isEqualTo("Arena Cineplex");
	}
	
	@Test 
		public void testGetAll() {
			List<Teatar> teatri = teatarService.findAll();
			assertThat(teatri.size()).isEqualTo(DB_COUNT);
		
	}
	
	@Test
	public void testGetAllBioskop(){
		Collection<Teatar> bioskopi = teatarService.getAllBioskop("bioskop");
		assertThat(bioskopi.size()).isEqualTo(DB_BISOKOPI);
		
	}
	
	@Test
    @Transactional
    @Rollback(true)
	public void testAdd() {
		Teatar t = new Teatar();
		t.setId(6l);
		t.setAdresa("Gogoljeva 15");
		t.setNaziv("NoviBioskop");
		t.setPromotivniOpis("prvi u trendu");
		t.setTip("bioskop");
		
		
		int dbSizeBeforeAdd = teatarService.findAll().size();
		
		teatarService.save(t);
		
				
        List<Teatar> teatri = teatarService.findAll();
        assertThat(teatri).hasSize(dbSizeBeforeAdd + 1);
        Teatar dbKor = teatri.get(teatri.size() - 1); 
        assertThat(dbKor.getId()).isEqualTo(id);
        assertThat(dbKor.getAdresa()).isEqualTo(adresa);
        assertThat(dbKor.getNaziv()).isEqualTo(naziv);
        assertThat(dbKor.getPromotivniOpis()).isEqualTo(promotivni);
        
	}
	
	
	@Test
	public void testfindByTipAndNaziv(){
		Collection<Teatar> pozoriste = teatarService.findByTipAndNaziv("pozoriste", nazivTeatra);
		assertThat(pozoriste.size()).isEqualTo(1);
		
		Collection<Teatar> bioskopi = teatarService.findByTipAndNaziv("bioskop", nazivBioskopa);
		assertThat(bioskopi.size()).isEqualTo(1);
		
		
		
	}

}
