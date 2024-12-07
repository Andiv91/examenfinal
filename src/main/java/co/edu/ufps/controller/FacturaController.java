package co.edu.ufps.controller;
import org.springframework.web.bind.annotation.RestController;
import co.edu.ufps.entityDTO.CompraResponseDTO;
import co.edu.ufps.entityDTO.FacturaRequestDTO;
import co.edu.ufps.entity.Compra;
import co.edu.ufps.entity.Tienda;
import co.edu.ufps.exception.ResourceNotFoundException;
import co.edu.ufps.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")
public class FacturaController {
	
	@Autowired
	CompraService compraService;
	
	@PostMapping("crear/{uuidTienda}")
	public ResponseEntity<CompraResponseDTO> crearFactura(@PathVariable String uuidTienda, @RequestBody FacturaRequestDTO compraDTO) {
		Compra compra = compraDTO.toEntity();
		Tienda tienda = new Tienda();
		tienda.setUuid(uuidTienda);
		compra.setTienda(tienda);
		
		try {
			compraService.crearFactura(compra);
			CompraResponseDTO resDTO = CompraResponseDTO.fromEntity(compra);
			resDTO.setMessage("La factura se ha creado correctamente con el número: " + compra.getId());
			return ResponseEntity.ok(resDTO);	
		} catch(ResourceNotFoundException e) {
			CompraResponseDTO resDTO = new CompraResponseDTO();
			resDTO.setStatus("error");
			resDTO.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resDTO);
		}
	}
}
