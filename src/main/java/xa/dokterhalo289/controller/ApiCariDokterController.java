package xa.dokterhalo289.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xa.dokterhalo289.service.CariDokterService;

@RestController
@RequestMapping(value="/api/modalcaridokter")
public class ApiCariDokterController {
	@Autowired
	private CariDokterService cds;
	
	@GetMapping(value="/lokasi")
	public List<Map<String, Object>> listLocation(){
		return cds.listLocation();
	}
	
	@GetMapping(value="/spesialisasi")
	public List<Map<String, Object>> listSpecialization(){
		return cds.listSpecialization();
	}
	
	@GetMapping(value="/treatment")
	public List<Map<String, Object>> listTreatment(){
		return cds.listTreatment();
	}
}
