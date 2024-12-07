package co.edu.ufps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entity.TipoProducto;

@Repository
public interface TipoProductoRepository extends JpaRepository<TipoProducto, Integer> {}
