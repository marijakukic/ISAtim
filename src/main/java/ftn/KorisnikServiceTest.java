package ftn;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ftn.model.Korisnik;
import ftn.service.KorisnikService;



@RunWith(SpringRunner.class)
@SpringBootTest
public class KorisnikServiceTest {

	@Autowired
	KorisnikService korisnikService;
	
	//listaKonstanti
	public static final int DB_COUNT = 7;
	public static final int DB_COUNTREG=4;
	
	public static final Long DB_ID = (long) 1;
	public static final Long DB_ID1 = (long) 2;
	public static final Long DB_ID2 = (long) 4;//ovog testiram za aktivacioni mail
	
	static String DB_FIRST_NAME = "marija";
	public static String DB_LAST_NAME = "kukic";
	public static String DB_EMAIL = "marijakukice2@gmail.com";
	public static String DB_LOZINKA = "marija";
	
	static String NEW_FIRST_NAME = "damir";
	public static String NEW_LAST_NAME = "kukic";
	public static String NEW_EMAIL = "damir.kkc@gmail.com";
	public static String NEW_LOZINKA = "damir";
	
	public static String ime = "milica";
	public static String prezime = "kukic";
	public static String email = "milica.kkc@gmail.com";
	public static String lozinka = "milica";

	
	@Test
	public void testFindAll() {
		List<Korisnik> korisnici = korisnikService.findAll();
		assertThat(korisnici).hasSize(DB_COUNT);
	}
	
	@Test 
		public void testGetUserDetails() {
			Korisnik dbKorisnik = korisnikService.findUserDetails(DB_ID1);
			assertThat(dbKorisnik).isNotNull();
			
			assertThat(dbKorisnik.getId()).isEqualTo(DB_ID1);
			assertThat(dbKorisnik.getIme()).isEqualTo(DB_FIRST_NAME);
	        assertThat(dbKorisnik.getPrezime()).isEqualTo(DB_LAST_NAME);
	        assertThat(dbKorisnik.getEmail()).isEqualTo(DB_EMAIL);
	        assertThat(dbKorisnik.getLozinka()).isEqualTo(DB_LOZINKA);
		
	}
	
	@Test
	public void testFindByEmail(){
		Korisnik k = korisnikService.findByEmail(DB_EMAIL);
		assertThat(k).isNotNull();
		assertThat(k.getEmail()).isEqualTo(DB_EMAIL);
		
	}
	
	@Test
    @Transactional
    @Rollback(true)
	public void testAdd() {
		Korisnik k = new Korisnik();
		k.setIme(ime);
		k.setPrezime(prezime);
		k.setEmail(email);
		k.setLozinka(lozinka);
		
		
		int dbSizeBeforeAdd = korisnikService.findAll().size();
		
		korisnikService.save(k);
		
				
        List<Korisnik> korisnici = korisnikService.findAll();
        assertThat(korisnici).hasSize(dbSizeBeforeAdd + 1);
        Korisnik dbKor = korisnici.get(korisnici.size() - 1); 
        assertThat(dbKor.getIme()).isEqualTo(ime);
        assertThat(dbKor.getPrezime()).isEqualTo(prezime);
        assertThat(dbKor.getEmail()).isEqualTo(email);
        assertThat(dbKor.getLozinka()).isEqualTo(lozinka);
        
	}
	
	@Test
	public void testSetActivated() {
		Korisnik dbKorisnik = korisnikService.findUserDetails(DB_ID2);
		assertThat(dbKorisnik.getPotvrdjenMail()).isEqualTo(false);
		
		Integer i = korisnikService.setActivated(true, dbKorisnik.getEmail());
	
		assertThat(i).isEqualTo((Integer)1);
		
		Korisnik dbKorisnik1 = korisnikService.findUserDetails(DB_ID2);
		assertThat(dbKorisnik1.getPotvrdjenMail()).isEqualTo(true);
		
	}
	
	@Test
	public void getAllRegUsersExceptMe(){
		Korisnik jaSam = korisnikService.findUserDetails(DB_ID1);
		ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
		korisnici = (ArrayList<Korisnik>) korisnikService.getAllRegUsersExceptMe(DB_ID1);
		assertThat(korisnici.size()).isEqualTo(DB_COUNTREG-1);
	}
	
	@Test
	public void testgetAllUsersExceptMeByName(){
		Korisnik jaSam = korisnikService.findUserDetails(DB_ID1);
		System.out.println(jaSam.getIme());
		ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
		korisnici = (ArrayList<Korisnik>) korisnikService.getAllRegUsersExceptMeByName(jaSam.getId(),jaSam.getIme());
		assertThat(korisnici.contains(jaSam)).isEqualTo(false);//moze i na ovaj nacin sve je vratio sem mene*/
	}
	
	@Test
	public void testgetAllUsersExceptMeBySurname(){
		Korisnik jaSam = korisnikService.findUserDetails(DB_ID1);
		ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
		korisnici = (ArrayList<Korisnik>) korisnikService.getAllRegUsersExceptMeBySurname(DB_ID1,jaSam.getPrezime());
		assertThat(korisnici.size()).isEqualTo(0);
	}
	
	@Test
	public void getAllRegUsersExceptMeByNameAndSurname(){
		Korisnik jaSam = korisnikService.findUserDetails(DB_ID1);
		ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
		korisnici = (ArrayList<Korisnik>) korisnikService.getAllRegUsersExceptMeByNameAndSurname(jaSam.getId(),jaSam.getIme(),jaSam.getPrezime());
		assertThat(korisnici.size()).isEqualTo(0);// ja sam marija a nema vise marija*/
	}
	
	
	
	
}