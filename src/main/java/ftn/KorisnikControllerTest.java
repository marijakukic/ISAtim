package ftn;

import static org.assertj.core.api.Assertions.assertThat;
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
import ftn.model.SkalaClanstva;
import ftn.service.KorisnikService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class KorisnikControllerTest {
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	//listaKonstanti
		public static final int DB_COUNT = 3;
		
		public static final Long DB_ID = (long) 1;
		public static final Long DB_ID1 = (long) 2;
		static String DB_FIRST_NAME = "marija";
		public static String DB_LAST_NAME = "kukic";
		public static String DB_EMAIL = "marijakukice2@gmail.com";
		public static String DB_LOZINKA = "marija";
		
		static String NEW_FIRST_NAME = "damir";
		public static String NEW_LAST_NAME = "kukic";
		public static String NEW_EMAIL = "damir.kkc@gmail.com";
		public static String NEW_LOZINKA = "damir";


	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	
	@Test
	public void testActiveStudent() throws Exception {
		mockMvc.perform(get("/getActiveUser")).andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.id").value(DB_ID1.intValue()))
				.andExpect(jsonPath("$.ime").value(DB_FIRST_NAME))
				.andExpect(jsonPath("$.prezime").value(DB_LAST_NAME))
				.andExpect(jsonPath("$.email").value(DB_EMAIL));
	}

	@Test
	public void testGetStudent() throws Exception {
		mockMvc.perform(get("/getUserDetails/" + DB_ID1)).andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.id").value(DB_ID1.intValue()))
				.andExpect(jsonPath("$.ime").value(DB_FIRST_NAME))
				.andExpect(jsonPath("$.prezime").value(DB_LAST_NAME))
				.andExpect(jsonPath("$.email").value(DB_EMAIL));
	}
	
	@Test
	public void testLogOut() throws Exception {
		mockMvc.perform(get("/logout")).andExpect(status().isOk());
		 assertThat(KorisnikService.aktivanKorisnik).isEqualTo(null);
		 }
	

	
	@Test
	@Transactional
	@Rollback(true)
	public void testEditStudent() throws Exception {
		Korisnik  k= new Korisnik();
		k.setIme("novi");
		k.setPrezime("novi");
		k.setEmail("novonovo@gmail.com");

		String json = TestUtil.json(k);
		this.mockMvc.perform(post("/editUser").contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSkalaClanstva() throws Exception {
		SkalaClanstva  k= new SkalaClanstva();
		k.setId((long)5);
		k.setBodoviZaPosetu(5);
		k.setBronzani(20);
		k.setBronzaniPopust(50);
		String json = TestUtil.json(k);
		this.mockMvc.perform(post("/skala/save").contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
	@Test
	public void testGetSkala() throws Exception {
		mockMvc.perform(get("/skala/get")).andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.id").value(1l));
				
	}
	
		
	@Test
	@Transactional
	@Rollback(true)
	public void testSaveKorisnik() throws Exception {
		Korisnik  k= new Korisnik();
		k.setIme("novi");
		k.setPrezime("novi");
		k.setEmail("novonovo@gmail.com");

		String json = TestUtil.json(k);
		this.mockMvc.perform(post("/registration").contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
	@Test
	public void testSetActivated() throws Exception {
		mockMvc.perform(get("/activate/" + DB_EMAIL)).andExpect(status().isOk());
	}
	
	@Test
	public void testLogin() throws Exception {
		mockMvc.perform(get("/login/" + DB_EMAIL)).andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.id").value(DB_ID1.intValue()))
				.andExpect(jsonPath("$.ime").value(DB_FIRST_NAME))
				.andExpect(jsonPath("$.prezime").value(DB_LAST_NAME))
				.andExpect(jsonPath("$.email").value(DB_EMAIL));
	}
	
	@Test
	public void testGetAllEMe() throws Exception {
		mockMvc.perform(get("/getAllUsersExceptMe/"+2+"/marija/kukic")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(0)));
	
	}
	
	
	
	
}
