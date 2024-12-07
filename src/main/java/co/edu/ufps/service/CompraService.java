package co.edu.ufps.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.ufps.entity.Cliente;
import co.edu.ufps.entity.Compra;
import co.edu.ufps.exception.ResourceNotFoundException;
import co.edu.ufps.repository.CajeroRepository;
import co.edu.ufps.repository.ClienteRepository;
import co.edu.ufps.repository.CompraRepository;

@Service
public class CompraService {
	@Autowired
	CompraRepository compraRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	CajeroRepository cajeroRepository;
	
	public Compra crearFactura(Compra compra) {
		if(compra.getCajero() == null) {
			throw new ResourceNotFoundException("No hay información del cajero.");
		}
		
		if(compra.getVendedor() == null) {
			throw new ResourceNotFoundException("No hay información del vendedor.");
		}
		
		if(compra.getCliente() == null) {
			throw new ResourceNotFoundException("No hay información del cliente.");
		}
		
		if(compra.getPagos() == null || compra.getPagos().size() == 0) {
			throw new ResourceNotFoundException("No hay medios de pagos asignado para esta compra.");
		}
		
		if(compra.getDetallesCompra() == null || compra.getDetallesCompra().size() == 0) {
			throw new ResourceNotFoundException("No hay productos asignados para esta compra.");
		}
		
		return compraRepository.save(compra);
	}
}
