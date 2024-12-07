package co.edu.ufps.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.ufps.entity.TipoDocumento;

@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Integer> {}
