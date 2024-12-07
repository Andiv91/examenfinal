package co.edu.ufps.entity;
import java.math.BigDecimal;
import java.security.Timestamp;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tienda_id")
    private Tienda tienda;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cajero_id")
    private Cajero cajero;
    private BigDecimal total;
    private BigDecimal impuestos;
    private Timestamp fecha;
    private String observaciones;
    
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<DetallesCompra> detallesCompra;
    
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<Pago> pagos;
}