package xa.dokterhalo289.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import xa.dokterhalo289.models.T_token;
@Repository
public interface T_tokenRepo extends JpaRepository<T_token, Long>{
	@Query(value = "SELECT * FROM T_token  WHERE token=?1", nativeQuery = true)
	List<T_token> getOtp(String token);
	
	
	@Query(value = "SELECT * FROM T_token  WHERE email=?1 AND token=?1", nativeQuery = true)
	List<T_token> getUlangOtp(String email, T_token token);

}
