package co.edu.ufps.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.ufps.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {}
