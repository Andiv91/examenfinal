package co.edu.ufps.entityDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
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
public class DataCreateCompraDTO {
	private Integer numero;
	private BigDecimal total;
	private String fecha;
	
	public static DataCreateCompraDTO fromEntity(Compra compra) {
		DataCreateCompraDTO dataDTO = new DataCreateCompraDTO();
		dataDTO.setFecha(LocalDate.now().toString());
		dataDTO.setTotal(compra.getTotal());
		dataDTO.setNumero(compra.getId());
		
		return dataDTO;
	}
}
