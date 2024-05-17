package xa.dokterhalo289.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xa.dokterhalo289.repositories.LocationLevelRepo;
import xa.dokterhalo289.repositories.SpesialisasiRepo;
import xa.dokterhalo289.repositories.TreatmentRepo;

@Service
@Transactional
public class CariDokterService {
	@Autowired
	private LocationLevelRepo llRepo;
	
	public List<Map<String, Object>> listLocation(){
		return llRepo.listLocation();
	}
	
	@Autowired
	private SpesialisasiRepo specRepo;
	
	public List<Map<String, Object>> listSpecialization() {
		return specRepo.listSpecialization();
	}

	
	
	@Autowired
	private TreatmentRepo doctreatRepo;
	
	public List<Map<String, Object>> listTreatment(){
		return doctreatRepo.listTreatment();
	}
}
