package co.edu.ufps.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entity.Cajero;
@Repository
public interface CajeroRepository extends JpaRepository<Cajero, Long> {
    Optional<Cajero> findByToken(String token);
}