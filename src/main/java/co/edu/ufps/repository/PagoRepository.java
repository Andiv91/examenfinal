package co.edu.ufps.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.ufps.entity.Pago;

public interface PagoRepository extends JpaRepository<Pago, Integer> {
}
