package xa.dokterhalo289.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xa.dokterhalo289.models.M_role;
import xa.dokterhalo289.repositories.M_roleRepo;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api")
public class ApiM_roleController {
	@Autowired M_roleRepo rolerepo;
	@GetMapping("/getcatrole")
	public ResponseEntity<List<M_role>> getAllCat() {
		try {
			List<M_role> catrole = this.rolerepo.findAll();
			return new ResponseEntity<List<M_role>>(catrole, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<M_role>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/getcatrole/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Long id) {
        try {
            List<M_role> catrole = this.rolerepo.roleId(id);
            if (catrole != null) {
                return new ResponseEntity<>(catrole, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("CategoryID dengan ID" + id + "tidak ditemukan");
            }

        } catch (Exception e) {
            return new ResponseEntity<M_role>(HttpStatus.NO_CONTENT);
        }
    }
}
