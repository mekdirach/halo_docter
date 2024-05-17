package xa.dokterhalo289.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import xa.dokterhalo289.models.M_biodata;
@Repository
public interface M_biodataRepo extends JpaRepository<M_biodata, Long>{
	
}
