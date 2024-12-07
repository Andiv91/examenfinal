package co.edu.ufps.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.ufps.entity.Tienda;
@Repository
public interface TiendaRepository extends JpaRepository<Tienda, Integer> {}
