package co.edu.ufps.entityDTO;
import co.edu.ufps.entity.Compra;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CompraResponseDTO {
	private String status;
	private String message;
	private DataCreateCompraDTO data;
	
	public static CompraResponseDTO fromEntity(Compra compra) {
		CompraResponseDTO resDTO = new CompraResponseDTO();
		if(compra != null) {
			resDTO.setData(CompraResponseDTO.fromEntity(compra));
		}
		return resDTO;
	}
}
