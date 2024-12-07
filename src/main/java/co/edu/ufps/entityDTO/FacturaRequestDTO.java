package co.edu.ufps.entityDTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import co.edu.ufps.entity.Compra;
import co.edu.ufps.entity.DetallesCompra;
import co.edu.ufps.entity.Pago;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FacturaRequestDTO {
	private BigDecimal impuesto;
	private ClienteDTO cliente;
	private List<DetallesCompraDTO> productos;
	
	@JsonProperty("medios_pago")
	private List<PagoDTO> mediosPago;
	private VendedorDTO vendedor;
	private CajeroDTO cajero;
	
	public Compra toEntity() {
		Compra compra = new Compra();
		compra.setImpuestos(this.impuesto);
		
		if(this.cliente != null) {
			compra.setCliente(this.cliente.toEntity());
		}
		
		if(this.productos != null) {
			List<DetallesCompra> productos = new ArrayList<DetallesCompra>();
			for(DetallesCompraDTO detalleCompra : this.productos) {
				productos.add(detalleCompra.toEntity());
			}
			
			compra.setDetallesCompra(productos);
		}

		if(this.mediosPago != null) {
			List<Pago> pagos = new ArrayList<Pago>();
			for(PagoDTO pago : this.mediosPago) {
				pagos.add(pago.toEntity());
			}
			compra.setPagos(pagos);
		}
		
		
		if(this.vendedor != null) {
			compra.setVendedor(this.vendedor.toEntity());
		}
		
		if(this.cajero != null) {
			compra.setCajero(this.cajero.toEntity());
		}
		
		return compra;
	}
}
