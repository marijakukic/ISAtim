package ftn;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import ftn.model.Korisnik;
import ftn.model.Prijateljstvo;
import ftn.service.KorisnikService;
import ftn.service.PrijateljstvoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PrijateljstvoControllerTest {
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	

	private MockMvc mockMvc;
	
	public static final Long DB_ID = (long) 3;
	

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private KorisnikService ks;
	
	@Autowired
	private PrijateljstvoService prijateljstvoService;

	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	

	@Test
	public void testListaPrijatelja() throws Exception {
		Korisnik k = new Korisnik();
		k = ks.findUserDetails((long)2);
		
		mockMvc.perform(get("/getFriendsList/" + 3)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$.[*].receiver.email").value(hasItem(k.getEmail())));
	}
	
		
	@Test
	@Transactional
	@Rollback(true)
	public void testFriendRequest() throws Exception {
		Prijateljstvo  p1= new Prijateljstvo();
		p1.setId((long)3);
		p1.setIdKorisnik1((long)1);
		p1.setIdKorisnik2((long)4);
		p1.setPoslatZahtev(true);
		p1.setPrijatelji(true);
		p1.setZahtevPoslao((long)1);
		

		String json = TestUtil.json(p1);
		this.mockMvc.perform(post("/sendFriendRequest/1/4").contentType(contentType).content(json)).andExpect(status().isOk());
	}

	
	@Test
	@Transactional
	@Rollback(true)
	public void testRefused() throws Exception {
		Prijateljstvo  p1 = prijateljstvoService.findBySenderAndRecieverIds(2l, 3l);
		mockMvc.perform(post("/acceptOrRefuseFriendRequest/2/3/false")).andExpect(status().isOk());
		try{
	    assertThat(prijateljstvoService.findBySenderAndRecieverIds(p1.getIdKorisnik1(),p1.getIdKorisnik2()).getPrijatelji()).isEqualTo(null);// obrisano je to prijateljstvo jer ga vise nema
		}catch(NullPointerException e){}
	}
	
	
	
	
	
	
}
