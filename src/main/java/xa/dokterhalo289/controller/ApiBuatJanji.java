package xa.dokterhalo289.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xa.dokterhalo289.models.M_customer_member;
import xa.dokterhalo289.models.M_medical_facility_category;
import xa.dokterhalo289.models.T_appointment;
import xa.dokterhalo289.models.T_doctor_treatment;
import xa.dokterhalo289.repositories.BuatJanjiRepo;
import xa.dokterhalo289.repositories.CustomerMemberRepo;
import xa.dokterhalo289.repositories.DoctorTreatmentRepo;
import xa.dokterhalo289.repositories.MedicalCategoryRepo;
import xa.dokterhalo289.service.BuatJanjiService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api")
public class ApiBuatJanji {
	@Autowired
	BuatJanjiRepo buatrepo;

	
	@Autowired
	CustomerMemberRepo cmr;
	
	@Autowired MedicalCategoryRepo mcr;
	
	@Autowired DoctorTreatmentRepo drp;
	
	/*@GetMapping(value = "konsultasi")
	public List<Map<String, Object>> konsul() {
		return bjs.konsul();
	}*/
	//M_customer_member
	@GetMapping("/konsul")
	public ResponseEntity<List<M_customer_member>> getKonsul() {
		try {
			List<M_customer_member> buatjanji = this.cmr.findAll();
			return new ResponseEntity<List<M_customer_member>>(buatjanji, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<M_customer_member>>(HttpStatus.NO_CONTENT);
		}
	}
	
	//
	@GetMapping("/faskes")
	public ResponseEntity<List<M_medical_facility_category>> getFaskes() {
		try {
			List<M_medical_facility_category> getfaskes = this.mcr.findAll();
			return new ResponseEntity<List<M_medical_facility_category>>(getfaskes, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<M_medical_facility_category>>(HttpStatus.NO_CONTENT);
		}
	}
	@GetMapping("/tindakandokter")
	public ResponseEntity<java.util.List<T_doctor_treatment>> getTindakan() {
		try {
			java.util.List<T_doctor_treatment> buatjanji = this.drp.findAll();
			return new ResponseEntity<List<T_doctor_treatment>>(buatjanji, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<T_doctor_treatment>>(HttpStatus.NO_CONTENT);
		}
	}
}
