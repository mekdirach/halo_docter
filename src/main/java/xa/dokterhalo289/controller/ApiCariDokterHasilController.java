package xa.dokterhalo289.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xa.dokterhalo289.service.CariDokterHasilService;

@RestController
@RequestMapping(value="/api/caridokter/")
public class ApiCariDokterHasilController {
	@Autowired
	private CariDokterHasilService cdhs;
	
	@GetMapping(value="alldoctor")
	public List<Map<String, Object>> alldoctor(){
		return cdhs.allDoctor();
	}
	
	@GetMapping(value="alldoctorschedule/{id}")
	public List<Map<String, Object>>allDoctorSchedule(@PathVariable long id){
		return cdhs.allDoctorSchedule(id);
	}
	
	@GetMapping(value="specializationbyid/{id}")
	public Map<String, Object>specializationById(@PathVariable long id){
		return cdhs.specializationById(id);
	}
	
	@GetMapping(value="locationid/{id}")
	public Map<String, Object> locationId(@PathVariable long id){
		return cdhs.locationId(id);
	}
	
	@GetMapping(value = "searchresult/{lokasi}/{fullname}/{spesialis}/{tindakan}")
	public List<Map<String, Object>>searchResult(@PathVariable String lokasi, @PathVariable String fullname,
			@PathVariable String spesialis, @PathVariable String tindakan){
		System.out.println(spesialis);
		System.out.println(lokasi);
		System.out.println(fullname);
		System.out.println(tindakan);
		
		
		lokasi=lokasi.equalsIgnoreCase("kosong")?"":lokasi;
		fullname=fullname.equalsIgnoreCase("kosong")?"":fullname;
		spesialis=spesialis.equalsIgnoreCase("kosong")?"":spesialis;
		tindakan=tindakan.equalsIgnoreCase("kosong")?"":tindakan; 
		return cdhs.searchResult(lokasi, fullname, spesialis, tindakan);
	}
}
