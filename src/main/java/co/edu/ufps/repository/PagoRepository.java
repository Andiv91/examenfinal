package co.edu.ufps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entity.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    // Métodos personalizados, si los necesitas
}