package xa.dokterhalo289.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import xa.dokterhalo289.models.M_medical_facility_category;
import xa.dokterhalo289.repositories.MedicalCategoryRepo;

@RestController
@CrossOrigin
@RequestMapping(value="/api")
public class ApiMedical_categoryController {
	
	@Autowired MedicalCategoryRepo catmerepo;

	@GetMapping("/category")
	public ResponseEntity<List<M_medical_facility_category>> getAllCatMedical() {
		try {
			List<M_medical_facility_category> medical = this.catmerepo.getMedicalItem();
			return new ResponseEntity<List<M_medical_facility_category>>(medical, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<M_medical_facility_category>>(HttpStatus.NO_CONTENT);
		}
	}
	
	
	 @GetMapping("/category/{id}")
	    public ResponseEntity<M_medical_facility_category> getProById(@PathVariable Long id) {
	        try {
	        	M_medical_facility_category categoryid = this.catmerepo.findById(id).orElse(null);
	            return new ResponseEntity<M_medical_facility_category>(categoryid, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<M_medical_facility_category>(HttpStatus.NO_CONTENT);
	        }
	    }
	    
	    @GetMapping("/categorycek/{cek}")
	    public ResponseEntity<List<M_medical_facility_category>> getCekById(@PathVariable("cek") String cek) {
	        try {
	        	List<M_medical_facility_category> categorycek = catmerepo.checking(cek);
	            return new ResponseEntity<>(categorycek, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }
	    }
	@PostMapping("/insertMedical")
	public ResponseEntity<M_medical_facility_category> insertCategory(
			@RequestBody M_medical_facility_category category) {
		try {
			category.setCreated_on(LocalDateTime.now());
			this.catmerepo.save(category);
			return new ResponseEntity<M_medical_facility_category>(category, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<M_medical_facility_category>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/editCategory/{id}")
    public ResponseEntity<M_medical_facility_category> editCategoryId(@RequestBody M_medical_facility_category category, @PathVariable("id") Long id) {
        try {
        	category.setId(id);
        	category.setCreated_by((long)1);
        	category.setDeleted_on(LocalDateTime.now());
        	category.setCreated_on(LocalDateTime.now());
            this.catmerepo.save(category);
            return new ResponseEntity<M_medical_facility_category>(category, HttpStatus.OK);
            
        } 
        catch (Exception e) {
        	e.printStackTrace();
            return new ResponseEntity<M_medical_facility_category>(HttpStatus.NO_CONTENT);
        }
    }
    
    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<M_medical_facility_category> deleteCategoryId(@RequestBody M_medical_facility_category category, @PathVariable Long id) {
        try {
        	category.setDeleted_on(LocalDateTime.now());
        	this.catmerepo.save(category);
            return new ResponseEntity<M_medical_facility_category>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<M_medical_facility_category>(HttpStatus.NO_CONTENT);
        }
    }
	
}
