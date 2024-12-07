package co.edu.ufps.entityDTO;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import co.edu.ufps.entity.Cliente;
import co.edu.ufps.entity.TipoDocumento;
import lombok.Data;

@Data
public class ClienteDTO {
	private String documento;
	private String nombre;
	
	@JsonProperty("tipo_documento")
	private String tipoDocumento;
	
	public static ClienteDTO fromEntity(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setDocumento(cliente.getDocumento());
		clienteDTO.setNombre(cliente.getNombre());
		
		if(cliente.getTipoDocumento() != null) {
			clienteDTO.setTipoDocumento(cliente.getTipoDocumento().getNombre());
		}
		
		return clienteDTO;
	}
	
    public Cliente toEntity() {
        Cliente cliente = new Cliente();
        cliente.setDocumento(this.getDocumento());
        cliente.setNombre(this.getNombre());

        if(this.getTipoDocumento() != null) {
        	TipoDocumento tipoDocumento = new TipoDocumento();
        	tipoDocumento.setNombre(this.getTipoDocumento());
        	cliente.setTipoDocumento(tipoDocumento);
        }

        return cliente;
    }
}
