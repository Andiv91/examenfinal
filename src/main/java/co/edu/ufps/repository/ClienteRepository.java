package co.edu.ufps.repository;

import co.edu.ufps.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByDocumentoAndTipoDocumentoNombre(String documento, String nombreTipoDocumento);
}
