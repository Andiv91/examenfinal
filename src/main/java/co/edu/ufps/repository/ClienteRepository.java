package co.edu.ufps.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.ufps.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	public List<Cliente> findAllByDocumento(String documento);
}
