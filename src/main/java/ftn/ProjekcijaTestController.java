package ftn;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
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

import ftn.model.Film;
import ftn.model.Projekcija;
import ftn.service.ProjekcijaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjekcijaTestController {
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private ProjekcijaService ps;
	
	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	
	@Test
	public void testSviBioskopi() throws Exception {
		mockMvc.perform(get("/projekcija/test")).andExpect(status().isOk());
	}

	
	@Test
	public void getAktivneProjekcijeuTeatru() throws Exception {
		mockMvc.perform(get("/projekcija/aktivne/teatar/1")).andExpect(status().isOk());
		 }
	
	@Test
	@Transactional
	@Rollback(true)
	public void obrisiProjekciju() throws Exception {
		Projekcija p = new Projekcija();
		p = ps.findOne(1l);
		String json = TestUtil.json(p);
		this.mockMvc.perform(post("/projekcija/obrisi/1").contentType(contentType).content(json)).andExpect(status().isOk());
		 }
	
	@Test
	public void omogucenaIzmenaProjekcije() throws Exception {
		mockMvc.perform(get("/projekcija/izmena/omogucena/1")).andExpect(status().isOk());
		 }
	
	@Test
	public void getAllFreeSeats() throws Exception {
		mockMvc.perform(get("/projekcija/getAllFreeSeats/1/1")).andExpect(status().isOk());
		 }
	
	@Transactional
	@Rollback(true)
	@Test
	public void getProjekcijeUTeatriByDate() throws Exception {
		
		this.mockMvc.perform(post("/rezervacija/saveRezervacija/1/1/2/true")).andExpect(status().isOk());
	}
	
	@Test
	public void otkazi() throws Exception {
		mockMvc.perform(get("/rezervacija/otkaziRezervaciju/1")).andExpect(status().isOk());
		 }
	
	
	@Transactional
	@Rollback(true)
	@Test
	public void saveFilm() throws Exception{
		Film f = new Film();
		f.setId(3l);
		f.setImeReditelja("Marija");
		f.setKratakOpis("Super");
		f.setPoster("slika");
		f.setProsecnaOcena((double)5);
		f.setSpisakGlumaca("Marija");
		f.setTrajanje("45");
		f.setZanr("horor");
		String json = TestUtil.json(f);
		this.mockMvc.perform(post("/film/save").contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
	@Test
	public void getFilm() throws Exception {
		mockMvc.perform(get("/film/get/1")).andExpect(status().isOk());
		 }
	
	@Test
	public void getFilmAll() throws Exception {
		mockMvc.perform(get("/film/get/svi")).andExpect(status().isOk());
		 }
	
	@Test
	public void getProjekcija() throws Exception {
		mockMvc.perform(get("/projekcija/get/1")).andExpect(status().isOk());
		 }
	
	

}
