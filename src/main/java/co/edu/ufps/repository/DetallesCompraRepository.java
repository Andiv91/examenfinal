package co.edu.ufps.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entity.DetallesCompra;
@Repository
public interface DetallesCompraRepository extends JpaRepository<DetallesCompra, Long> {
    // Métodos personalizados, si los necesitas
}