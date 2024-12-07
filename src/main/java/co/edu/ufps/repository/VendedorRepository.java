package co.edu.ufps.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.ufps.entity.Vendedor;
@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {
   public Optional<Vendedor> findByDocumento(String documento);
}