package co.edu.ufps.entityDTO;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonAlias;
import co.edu.ufps.entity.Cliente;
import co.edu.ufps.entity.DetallesCompra;
import co.edu.ufps.entity.Producto;
import co.edu.ufps.entity.TipoDocumento;
import lombok.Data;

@Data
public class DetallesCompraDTO {
    private String referencia;
    private Integer cantidad;
    private BigDecimal descuento;

    public static DetallesCompraDTO fromEntity(DetallesCompra detallesCompra) {
        DetallesCompraDTO dto = new DetallesCompraDTO();
        
        if(detallesCompra.getProducto() != null) {
        	dto.setReferencia(detallesCompra.getProducto().getReferencia());
        }
        
        dto.setCantidad(detallesCompra.getCantidad());
        dto.setDescuento(detallesCompra.getDescuento());
        return dto;
    }
    
    public DetallesCompra toEntity() {
        DetallesCompra detallesCompra = new DetallesCompra();
        
        if(this.referencia != null) {
        	Producto producto = new Producto();
        	producto.setReferencia(referencia);
        	
        	detallesCompra.setProducto(producto);
        }
        
        detallesCompra.setCantidad(this.cantidad);
        detallesCompra.setDescuento(this.descuento);
        return detallesCompra;
    }
    
}
