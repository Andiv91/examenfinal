package co.edu.ufps.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.ufps.entity.Cajero;

public interface CajeroRepository extends JpaRepository<Cajero, Integer> {
	public Optional<Cajero> findOneByToken(String token);
}
