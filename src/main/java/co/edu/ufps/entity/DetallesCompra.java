package co.edu.ufps.entity;
import java.math.BigDecimal;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "detalles_compra")
@Data
public class DetallesCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "compra_id")
    private Compra compra;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "producto_id")
    private Producto producto;
    private Integer cantidad;
    private BigDecimal precio;
    private BigDecimal descuento;
}