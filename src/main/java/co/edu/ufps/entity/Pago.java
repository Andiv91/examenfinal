package co.edu.ufps.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "compra_id", nullable = false)
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "tipo_pago_id", nullable = false)
    private TipoPago tipoPago;

    @Column(length = 20)
    private String tarjetaTipo; // Solo acepta "VISA" o "MASTERCARD"

    @Column
    private double valor;

    private Integer cuotas;

    // Constructor vacío necesario para JPA
    public Pago() {}

    // Constructor con parámetros para la creación del objeto
    public Pago(Compra compra, TipoPago tipoPago, String tarjetaTipo, Integer cuotas, double valor) {
        this.compra = compra;
        this.tipoPago = tipoPago;
        this.tarjetaTipo = tarjetaTipo;
        this.cuotas = cuotas;
        this.valor = valor;
    }
}