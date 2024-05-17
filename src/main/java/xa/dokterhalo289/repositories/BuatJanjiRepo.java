package xa.dokterhalo289.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import xa.dokterhalo289.models.T_appointment;

public interface BuatJanjiRepo extends JpaRepository<T_appointment, Long>{

	/*@Query(value = "Select mb.fullname, mcr.name from m_customer_member mcm \r\n"
			+ "join m_customer mc ON mcm.customer_id = mc.id\r\n"
			+ "JOIN m_customer_relation mcr ON mcm.customer_relation_id = mcr.id \r\n"
			+ "JOIN m_biodata mb ON mb.id = mc.biodata_id\r\n"
			+ "Group by mb.fullname, mcr.name order by fullname",nativeQuery = true)
	List<Map<String, Object>> konsul();*/

}
