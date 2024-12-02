package co.edu.ufps.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.ufps.entity.TipoPago;
@Repository
public interface TipoPagoRepository extends JpaRepository<TipoPago, Long> {
    Optional<TipoPago> findByNombre(String nombre);
}