package co.edu.ufps.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.ufps.entity.Compra;

public interface CompraRepository extends JpaRepository<Compra, Integer> {
}
