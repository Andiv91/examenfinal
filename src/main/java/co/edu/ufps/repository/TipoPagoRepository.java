package co.edu.ufps.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.ufps.entity.TipoPago;
@Repository

public interface TipoPagoRepository extends JpaRepository<TipoPago, Integer> {
}
