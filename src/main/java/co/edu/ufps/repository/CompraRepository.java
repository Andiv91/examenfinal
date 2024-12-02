package co.edu.ufps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entity.Compra;
@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
    // Métodos personalizados, si los necesitas
}