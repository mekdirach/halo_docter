package xa.dokterhalo289.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xa.dokterhalo289.repositories.DoctorOfficeScheduleRepo;
import xa.dokterhalo289.repositories.LocationLevelRepo;
import xa.dokterhalo289.repositories.SpesialisasiRepo;

@Service
@Transactional
public class CariDokterHasilService {
	@Autowired
	private DoctorOfficeScheduleRepo dosRepo;
	@Autowired
	private SpesialisasiRepo specRepo;
	@Autowired 
	private LocationLevelRepo llRepo;
	
	public List<Map<String, Object>> allDoctor() {
		return dosRepo.allDoctor();
	}
	
	public List<Map<String, Object>> allDoctorSchedule(long id) {
		return dosRepo.allDoctorSchedule(id);
	}
	
	public List<Map<String, Object>> searchResult(String lokasi, String fullname, String spesialis, String tindakan) {
		return dosRepo.searchResult(lokasi, fullname, spesialis, tindakan);
	}

	public Map<String, Object> specializationById(Long id) {
		return specRepo.specializationById(id);
	}
	
	public Map<String, Object> locationId(long id) {
		return llRepo.locationId(id);
	}
}
