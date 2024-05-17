package xa.dokterhalo289.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import xa.dokterhalo289.models.M_specialization;

@Repository
public interface SpesialisasiRepo extends JpaRepository<M_specialization, Long> {
	@Query(value = "select id, name, is_delete from M_specialization WHERE is_delete = false",nativeQuery = true)
	List<Map<String, Object>> listSpecialization();
	
	@Query(value = "select id, name from M_specialization where Id=?1",nativeQuery = true)
	Map<String, Object> specializationById(long id);
	
	@Query(value = "SELECT name FROM M_specialization WHERE Name=?1", nativeQuery = true)
	String specializationByName(String name);
	
	@Query(value = "SELECT is_delete FROM m_specialization WHERE is_delete = ?1", nativeQuery = true)
	String isDeletedTrue(Boolean isDelete);
	
	@Query(value = "SELECT ms.name\r\n"
			+ "FROM m_specialization ms\r\n"
			+ "JOIN t_current_doctor_specialization tcds\r\n"
			+ "ON ms.id = tcds.specialization_id\r\n"
			+ "WHERE Name = ?1", nativeQuery = true)
	String cekListSpecializationDoctor(String name);
	
	@Query(value="select mb.fullname\r\n"
			+ "from M_specialization ms\r\n"
			+ "JOIN m_user mu\r\n"
			+ "ON ms.created_by = mu.id\r\n"
			+ "JOIN m_biodata mb\r\n"
			+ "ON mu.biodata_id = mb.id\r\n"
			+ "WHERE ms.is_delete = false", nativeQuery = true)
	String getCreateByName();
	
	@Query(value="SELECT tcds.specialization_id \r\n"
			+ "FROM m_specialization ms\r\n"
			+ "JOIN t_current_doctor_specialization tcds ON tcds.specialization_id = ms.id", nativeQuery=true)
	Long getSpecializationId();
}
