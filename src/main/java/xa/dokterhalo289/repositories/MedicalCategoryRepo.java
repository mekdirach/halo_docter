package xa.dokterhalo289.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import xa.dokterhalo289.models.M_medical_facility_category;

@Repository
public interface MedicalCategoryRepo extends JpaRepository<M_medical_facility_category, Long> {
	@Query(value = "SELECT * FROM m_medical_facility_category item WHERE  LOWER(item.Name)= LOWER(?1) AND item.Is_delete=false ", nativeQuery = true)
	List<M_medical_facility_category> checking(String name);

	@Query(value = "SELECT * FROM m_medical_facility_category item WHERE item.Is_delete=false", nativeQuery = true)
	List<M_medical_facility_category> getMedicalItem();
}
