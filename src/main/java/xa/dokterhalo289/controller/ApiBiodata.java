package xa.dokterhalo289.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xa.dokterhalo289.models.M_biodata;
import xa.dokterhalo289.models.M_user;
import xa.dokterhalo289.repositories.M_biodataRepo;
import xa.dokterhalo289.service.BuatJanjiService;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/api")
public class ApiBiodata {
	@Autowired M_biodataRepo biodatarepo;
	@PostMapping("/daftarbiodata")
	public ResponseEntity<M_biodata> sendBio(@RequestBody M_biodata biodata) {
		try {

			LocalDateTime datetime = LocalDateTime.now();
			biodata.setCreated_by((long) 1);
			biodata.setCreated_on(datetime);
			//m_biodata.setFullname(fullname);
		//	m_biodata.setMobile_phone(nomberhp);
			this.biodatarepo.save(biodata);

			return new ResponseEntity<M_biodata>(biodata, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
