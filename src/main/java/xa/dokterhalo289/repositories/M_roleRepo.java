package xa.dokterhalo289.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import xa.dokterhalo289.models.M_role;
@Repository
public interface M_roleRepo extends JpaRepository<M_role, Long>{
	 @Query("FROM M_role WHERE id = ?1")
	    List<M_role> roleId(Long id);
}
