package ftn;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import ftn.model.Film;
import ftn.model.Sala;
import ftn.model.Teatar;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeatarTestController {
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	

	@Test
	public void getTeatar() throws Exception {
		mockMvc.perform(get("/teatar/get/1" )).andExpect(status().isOk());
				
	}
	
	@Test
	public void sviBioskopi() throws Exception {
		mockMvc.perform(get("/bioskop/sviBioskopi")).andExpect(status().isOk());
		
	}
	
	@Test
	public void svaPozorista() throws Exception {
		mockMvc.perform(get("/pozorista/svaPozorista")).andExpect(status().isOk());
		
	}
	
	@Test
	public void search() throws Exception {
		mockMvc.perform(get("/searchPB/Arena Cineplex/bioskop")).andExpect(status().isOk());
		
	}
	
	@Test
	public void getSale() throws Exception {
		mockMvc.perform(get("/teatar/getSale/1")).andExpect(status().isOk());
		
	}
	
	@Test
	public void getSegment() throws Exception {
		mockMvc.perform(get("/segment/getAllSalaSegments/1")).andExpect(status().isOk());
		
	}
	
	@Test
	public void testActiveStudent() throws Exception {
		mockMvc.perform(get("/segment/getAllSalaSeats/1")).andExpect(status().isOk());
		
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void registration1() throws Exception {
		
		Teatar t = new Teatar();
		t.setAdresa("Puskinova");
		t.setId(4l);
		t.setNaziv("Pozoriste mladih");
		t.setPromotivniOpis("sve je super");
		t.setTip("bioskop");
		String json = TestUtil.json(t);
		this.mockMvc.perform(post("/registrationTeatar").contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void saveSala() throws Exception {
		
		Sala s = new Sala();
		s.setId(3l);
		s.setNaziv("Sala 3");
		s.setTeatarId(1l);
		String json = TestUtil.json(s);
		this.mockMvc.perform(post("/sala/saveSala").contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void saveFilm() throws Exception {
		
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
	

}
