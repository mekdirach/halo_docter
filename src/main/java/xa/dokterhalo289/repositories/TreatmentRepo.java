package xa.dokterhalo289.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import xa.dokterhalo289.models.T_doctor_treatment;

@Repository
public interface TreatmentRepo extends JpaRepository<T_doctor_treatment, Long>{

	@Query(value = "select id, doctor_id, name from t_doctor_treatment where doctor_id = ?1 and is_delete = false",nativeQuery = true)
	List<Map<String, Object>> doctor_treatment(long doctor_id);
	
	@Query(value = "select id, name from t_doctor_treatment where is_delete = false",nativeQuery = true)
	List <Map<String, Object>> listTreatment();
 	
	@Query(value = "select id, name from t_doctor_treatment where id = ?1 ",nativeQuery = true)
	Map<String, Object> listTreatmentById(long id);
	
 	@Modifying
	@Query(value = "insert into t_doctor_treatment(doctor_id, name, created_by, created_on, is_delete)values(?1,?2,99,now(),false)",nativeQuery = true)
    int insertDoctorTreatment(long doctor_id, String name);
 	
	@Modifying
 	@Query(value="update t_doctor_treatment set is_delete=true, deleted_on=now(), deleted_by=99 where id=?1 ",nativeQuery = true)
 	int deleteDoctorTreatment(long id);
	
 	@Query(value = "select name from t_doctor_treatment where lower(name) = ?1 and is_delete=false",nativeQuery = true)
 	String cariTreatment(String name);
 	
 	@Query(value = "SELECT * FROM t_doctor_treatment dt WHERE dt.Doctor_id =?1", nativeQuery = true)
    List<T_doctor_treatment> listtreatments(Long id);
}
