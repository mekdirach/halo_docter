package xa.dokterhalo289.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import xa.dokterhalo289.models.M_user;
@Repository
public interface M_userRepo extends JpaRepository<M_user, Long> {

	@Query(value = "SELECT * FROM M_user m WHERE m.email=?1 AND m.password=?2", nativeQuery = true)
	List<M_user> getLogin(String Email, String Password);

	@Query(value = "SELECT * FROM M_user  WHERE email=?1", nativeQuery = true)
	List<M_user> getEmail(String Email);
	
	@Query(value = "SELECT * FROM M_user  WHERE password=?1", nativeQuery = true)
	List<M_user> getPass(String Email);

	
}
