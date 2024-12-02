package co.edu.ufps.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DetallesCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "compra_id", nullable = false)
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double precio;

    @Column(precision = 5, scale = 2)
    private Double descuento;

    // Constructor vacío necesario para JPA
    public DetallesCompra() {}

    // Constructor con parámetros
    public DetallesCompra(Compra compra, Producto producto, Integer cantidad, Double precio, Double descuento) {
        this.compra = compra;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
    }
}
