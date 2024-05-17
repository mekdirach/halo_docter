package xa.dokterhalo289.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import xa.dokterhalo289.models.M_location_level;

@Repository
public interface LocationLevelRepo extends JpaRepository<M_location_level, Long>{
	@Query(value = "select id, name from m_location_level",nativeQuery = true)
	List<Map<String, Object>> listLocation();
	
	@Query(value = "select id, name from m_location_level where id=?1",nativeQuery = true)
	Map<String, Object> locationId(long id);
}
