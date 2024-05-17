package xa.dokterhalo289.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import xa.dokterhalo289.models.T_doctor_office_schedule;

@Repository
public interface DoctorOfficeScheduleRepo extends JpaRepository<T_doctor_office_schedule, Long> {
	
	//tampil semua dokter tanpa schedule
		//SALAH
		@Query(
				value = "select\r\n"
				+ "tdo.doctor_id,\r\n"
				+ "mb.fullname,\r\n"
				+ "mb.image_path,\r\n"
				+ "ms.id as specialization_id,\r\n"
				+ "ms.name as specialization,\r\n"
				+ "string_agg(tdt.name, ', ') as tindakan,\r\n"
				+ "extract(year from min(tdo.created_on)) as tahun_awal,\r\n"
				+ "extract(year from now()) as tahun_akhir\r\n"
				+ "from t_doctor_office tdo\r\n"
				+ "join m_doctor md on tdo.doctor_id = md.id\r\n"
				+ "join m_biodata mb on md.biodata_id = mb.id\r\n"
				+ "join t_current_doctor_specialization tcd on tdo.doctor_id = tcd.doctor_id\r\n"
				+ "join m_specialization ms on tcd.specialization_id = ms.id\r\n"
				+ "join t_doctor_treatment tdt on tdt.doctor_id = tdo.doctor_id\r\n"
				+ "group by tdo.doctor_id, mb.fullname, ms.id, ms.name, mb.image_path ORDER BY tdo.doctor_id asc",nativeQuery = true)
			List<Map<String, Object>> allDoctor();
						
		//tampil schedule dokter didalam card
		@Query(value = "select tdos.doctor_id, \r\n"
				+ "mmf.name as nama_RS,\r\n"
				+ "mmfs.day,\r\n"
				+ "to_char(now(), 'Day') AS day_now,\r\n"
				+ "CASE WHEN mmfs.day = 'Minggu' THEN 'Sunday'\r\n"
				+ "	WHEN mmfs.day = 'Senin' THEN 'Monday'\r\n"
				+ "	WHEN mmfs.day = 'Selasa' THEN 'Tuesday'\r\n"
				+ "	WHEN mmfs.day = 'Rabu' THEN 'Wednesday'\r\n"
				+ "	WHEN mmfs.day = 'Kamis' THEN 'Thursday'\r\n"
				+ "	WHEN mmfs.day = 'Jumat' THEN 'Friday'\r\n"
				+ "	WHEN mmfs.day = 'Sabtu' THEN 'Saturday'\r\n"
				+ "		 ELSE 'other' END\r\n"
				+ "		 AS day_schedule,\r\n"
				+ "mmfs.time_schedule_start,\r\n"
				+ "mmfs.time_schedule_end,\r\n"
				+ "mll.name as location_level,\r\n"
				+ "ml.name as location\r\n"
				+ "from t_doctor_office_schedule tdos\r\n"
				+ "join m_medical_facility_schedule mmfs on tdos.medical_facility_schedule_id = mmfs.id\r\n"
				+ "join m_medical_facility mmf on mmfs.medical_facility_id = mmf.id\r\n"
				+ "join m_location ml on mmf.location_id = ml.id\r\n"
				+ "join m_location_level mll on ml.location_level_id = mll.id\r\n"
				+ "where tdos.doctor_id = ?1 and tdos.is_delete = false order by tdos.doctor_id",nativeQuery = true)
			List<Map<String, Object>> allDoctorSchedule(long id);
				
		//caridokterfix
		@Query(value = "SELECT\r\n"
				+ "tdos.doctor_id,\r\n"
				+ "mb.fullname,\r\n"
				+ "mb.image_path,\r\n"
				+ "ms.name AS spesialis,\r\n"
				+ "extract(year from now()) - (SELECT EXTRACT (YEAR FROM MIN(created_on)) FROM t_doctor_office_schedule WHERE doctor_id=tdos.doctor_id) as tahun_pengalaman,\r\n"
				+ "to_char(now(), 'Day') AS day_now,\r\n"
				+ "CASE WHEN mmfs.day = 'Minggu' THEN 'Sunday'\r\n"
				+ "	WHEN mmfs.day = 'Senin' THEN 'Monday'\r\n"
				+ "	WHEN mmfs.day = 'Selasa' THEN 'Tuesday'\r\n"
				+ "	WHEN mmfs.day = 'Rabu' THEN 'Wednesday'\r\n"
				+ "	WHEN mmfs.day = 'Kamis' THEN 'Thursday'\r\n"
				+ "	WHEN mmfs.day = 'Jumat' THEN 'Friday'\r\n"
				+ "	WHEN mmfs.day = 'Sabtu' THEN 'Saturday'\r\n"
				+ "		ELSE 'other' END\r\n"
				+ "		AS day_schedule,\r\n"
				+ "mmfs.time_schedule_start,\r\n"
				+ "mmfs.time_schedule_end,\r\n"
				+ "string_agg(tdt.name, ', ') AS tindakan,\r\n"
				+ "mll.name AS location_level\r\n"
				+ "FROM t_doctor_office_schedule tdos\r\n"
				+ "JOIN m_medical_facility_schedule mmfs ON tdos.medical_facility_schedule_id = mmfs.id\r\n"
				+ "JOIN m_medical_facility mmf ON mmfs.medical_facility_id = mmf.id\r\n"
				+ "JOIN m_location ml ON mmf.location_id = ml.id\r\n"
				+ "JOIN m_doctor md ON tdos.doctor_id = md.id\r\n"
				+ "JOIN m_biodata mb ON md.biodata_id = mb.id\r\n"
				+ "JOIN t_current_doctor_specialization tcd ON tdos.doctor_id = tcd.doctor_id\r\n"
				+ "JOIN m_specialization ms ON tcd.specialization_id = ms.id\r\n"
				+ "JOIN m_location_level mll ON ml.location_level_id = mll.id\r\n"
				+ "JOIN t_doctor_treatment tdt ON tdt.doctor_id = md.id\r\n"
				+ "WHERE (mll.name like %?1% AND lower(mb.fullname) like %?2% AND ms.name LIKE %?3% AND tdt.name LIKE %?4%) and tdos.is_delete = false\r\n"
				+ "GROUP BY tdos.doctor_id, mb.fullname, mb.image_path, ms.name, tdos.created_on,mmf.name,\r\n"
				+ "mmfs.day, mll.name, ml.name, mmfs.time_schedule_start, mmfs.time_schedule_end",nativeQuery = true)
			List<Map<String, Object>> searchResult(String lokasi, String fullname, String spesialis, String tindakan);
}
