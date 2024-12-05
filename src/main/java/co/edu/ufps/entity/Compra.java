package co.edu.ufps.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "tienda_id", nullable = false)
    private Tienda tienda;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;

    @ManyToOne
    @JoinColumn(name = "cajero_id", nullable = false)
    private Cajero cajero;

    @Column
    private double total;

    @Column
    private double impuestos;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(length = 1000)
    private String observaciones;

    // Relación OneToMany con DetallesCompra
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallesCompra> detallesCompra;

    // Constructor vacío necesario para JPA
    public Compra() {}

    // Constructor con parámetros
    public Compra(Cliente cliente, Tienda tienda, Vendedor vendedor, Cajero cajero,
    		double total, double impuestos, LocalDateTime fecha, String observaciones) {
        this.cliente = cliente;
        this.tienda = tienda;
        this.vendedor = vendedor;
        this.cajero = cajero;
        this.total = total;
        this.impuestos = impuestos;
        this.fecha = fecha;
        this.observaciones = observaciones;
    }
}
