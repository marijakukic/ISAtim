package ftn;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import ftn.model.Korisnik;
import ftn.model.Prijateljstvo;
import ftn.service.PrijateljstvoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PrijateljstvoServiceTest {
	
	@Autowired
	PrijateljstvoService prijateljstvoService;
	
	//listaKonstanti
	public static final int DB_COUNT = 6;
	
	
	public static Long ID_KOR1 = (long)2;
	public static Long ID_KOR2 = (long)6;
	public static Long IDPRIJATELJSTVA = (long)7;

	
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSaveFriends() {
		Prijateljstvo p = new Prijateljstvo();
		p.setId(IDPRIJATELJSTVA);
		p.setIdKorisnik1(ID_KOR1);
		p.setIdKorisnik2(ID_KOR2);
		p.setPoslatZahtev(true);
		p.setPrijatelji(true);
		p.setZahtevPoslao(ID_KOR1);
		
		int dbSizeBeforeAdd = prijateljstvoService.findAll().size();
		
		prijateljstvoService.save(p);
		
				
        List<Prijateljstvo> prijateljstvo = prijateljstvoService.findAll();
        assertThat(prijateljstvo).hasSize(dbSizeBeforeAdd + 1);
        Prijateljstvo dbP = prijateljstvo.get(prijateljstvo.size() - 1); 
        assertThat(dbP.getIdKorisnik1()).isEqualTo(ID_KOR1);
        assertThat(dbP.getIdKorisnik2()).isEqualTo(ID_KOR2);
         
      
	}
	
	@Test
	public void testFindAll() {
		List<Prijateljstvo> prijatelji = prijateljstvoService.findAll();
		assertThat(prijatelji).hasSize(DB_COUNT);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteFriends() {
		Prijateljstvo p = new Prijateljstvo();
		p.setId((long)2);
		p.setIdKorisnik1((long)1);
		p.setIdKorisnik2((long)3);
		p.setPoslatZahtev(true);
		p.setPrijatelji(true);
		p.setZahtevPoslao((long)1);
		
		int dbSizeBeforeDelete = prijateljstvoService.findAll().size();
		
		prijateljstvoService.delete(p);
		
				
        List<Prijateljstvo> prijateljstvo = prijateljstvoService.findAll();
        assertThat(prijateljstvo).hasSize(dbSizeBeforeDelete - 1);
  
      
	}
	
	@Test
	public void testfindBySenderAndRecieverIds(){
		Prijateljstvo p = prijateljstvoService.findBySenderAndRecieverIds((long)2,(long)4);
		assertThat(p.getId()).isEqualTo(3);
		
	}
	
	@Test
	public void testGetSviPrijateljiKorisnika(){
		ArrayList<Prijateljstvo> prijatelji = new ArrayList<Prijateljstvo>();
		prijatelji = (ArrayList<Prijateljstvo>) prijateljstvoService.listaPrijatelja(ID_KOR1);
		assertThat(prijatelji.size()).isEqualTo(3);
	}
	
	@Test
	public void testProveraDaLiSuPrijatelji(){
		ArrayList<Prijateljstvo> nisuprijatelji = new ArrayList<Prijateljstvo>();
		nisuprijatelji = (ArrayList<Prijateljstvo>) prijateljstvoService.vecPrijatelji(ID_KOR1,ID_KOR2);
		assertThat(nisuprijatelji.size()).isEqualTo(0);
		
		ArrayList<Prijateljstvo> jesuprijatelji = new ArrayList<Prijateljstvo>();
		jesuprijatelji = (ArrayList<Prijateljstvo>) prijateljstvoService.vecPrijatelji((long)3,(long)2);
		assertThat(jesuprijatelji.size()).isEqualTo(1);
	}

}
