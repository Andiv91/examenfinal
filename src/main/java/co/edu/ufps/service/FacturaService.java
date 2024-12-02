package co.edu.ufps.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import co.edu.ufps.entity.Cajero;
import co.edu.ufps.entity.Cliente;
import co.edu.ufps.entity.Compra;
import co.edu.ufps.entity.DetallesCompra;
import co.edu.ufps.entity.Pago;
import co.edu.ufps.entity.Producto;
import co.edu.ufps.entity.Tienda;
import co.edu.ufps.entity.TipoPago;
import co.edu.ufps.entity.Vendedor;
import co.edu.ufps.entityDTO.ConsultarFacturaRequestDTO;
import co.edu.ufps.entityDTO.ConsultarFacturaResponseDTO;
import co.edu.ufps.entityDTO.FacturaRequestDTO;
import co.edu.ufps.entityDTO.FacturaResponseDTO;
import co.edu.ufps.exception.CustomException;
import co.edu.ufps.repository.CajeroRepository;
import co.edu.ufps.repository.ClienteRepository;
import co.edu.ufps.repository.CompraRepository;
import co.edu.ufps.repository.ProductoRepository;
import co.edu.ufps.repository.TiendaRepository;
import co.edu.ufps.repository.TipoPagoRepository;
import co.edu.ufps.repository.VendedorRepository;
import java.time.LocalDateTime;
@Service
public class FacturaService {

    private final CompraRepository compraRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    private final TipoPagoRepository tipoPagoRepository;
    private final VendedorRepository vendedorRepository;
    private final CajeroRepository cajeroRepository;
    private final TiendaRepository tiendaRepository;

    public FacturaService(CompraRepository compraRepository, ClienteRepository clienteRepository,
                          ProductoRepository productoRepository, TipoPagoRepository tipoPagoRepository,
                          VendedorRepository vendedorRepository, CajeroRepository cajeroRepository,
                          TiendaRepository tiendaRepository) {
        this.compraRepository = compraRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
        this.tipoPagoRepository = tipoPagoRepository;
        this.vendedorRepository = vendedorRepository;
        this.cajeroRepository = cajeroRepository;
        this.tiendaRepository = tiendaRepository;
    }

    public FacturaResponseDTO crearFactura(String tiendaUuid, FacturaRequestDTO request) {
        // Validar tienda
        Tienda tienda = tiendaRepository.findByUuid(tiendaUuid)
                .orElseThrow(() -> new CustomException("La tienda con el UUID proporcionado no existe", HttpStatus.NOT_FOUND));

        // Validar cliente (registrar si no existe)
        Cliente cliente = clienteRepository.findByDocumentoAndTipoDocumento(request.getCliente().getDocumento(),
                request.getCliente().getTipoDocumento())
                .orElseGet(() -> registrarCliente(request.getCliente()));

        // Validar vendedor
        Vendedor vendedor = vendedorRepository.findByDocumento(request.getVendedor().getDocumento())
                .orElseThrow(() -> new CustomException("El vendedor no existe en la tienda", HttpStatus.NOT_FOUND));

        // Validar cajero y token
        Cajero cajero = cajeroRepository.findByToken(request.getCajero().getToken())
                .orElseThrow(() -> new CustomException("El token no corresponde a ningún cajero en la tienda", HttpStatus.NOT_FOUND));
        if (!cajero.getTienda().equals(tienda)) {
            throw new CustomException("El cajero no está asignado a esta tienda", HttpStatus.FORBIDDEN);
        }

        // Validar productos
        double totalFactura = 0;
        for (FacturaRequestDTO.ProductoRequestDTO prodReq : request.getProductos()) {
            Producto producto = productoRepository.findByReferencia(prodReq.getReferencia())
                    .orElseThrow(() -> new CustomException(
                            String.format("La referencia del producto %s no existe, por favor revisar los datos", prodReq.getReferencia()),
                            HttpStatus.NOT_FOUND));

            if (producto.getCantidad() < prodReq.getCantidad()) {
                throw new CustomException("La cantidad a comprar supera el máximo del producto en tienda", HttpStatus.FORBIDDEN);
            }
            totalFactura += (producto.getPrecio() * prodReq.getCantidad()) - prodReq.getDescuento();
        }

        // Validar medios de pago
        double totalPagos = 0;
        for (FacturaRequestDTO.MedioPagoRequestDTO pagoReq : request.getMediosPago()) {
            TipoPago tipoPago = tipoPagoRepository.findByNombre(pagoReq.getTipoPago())
                    .orElseThrow(() -> new CustomException("Tipo de pago no permitido en la tienda", HttpStatus.FORBIDDEN));

            totalPagos += pagoReq.getValor();
        }

        if (request.getMediosPago().isEmpty()) {
            throw new CustomException("No hay medios de pagos asignados para esta compra", HttpStatus.NOT_FOUND);
        }

        if (totalFactura != totalPagos) {
            throw new CustomException("El valor de la factura no coincide con el valor total de los pagos", HttpStatus.FORBIDDEN);
        }

        // Registrar compra
        Compra compra = new Compra(cliente, tienda, vendedor, cajero, totalFactura, request.getImpuesto(), LocalDateTime.now(), null);
        compra = compraRepository.save(compra);

        // Registrar detalles de compra
        for (FacturaRequestDTO.ProductoRequestDTO prodReq : request.getProductos()) {
            Producto producto = productoRepository.findByReferencia(prodReq.getReferencia()).get();
            producto.setCantidad(producto.getCantidad() - prodReq.getCantidad());
            productoRepository.save(producto);

            DetallesCompra detalles = new DetallesCompra(compra, producto, prodReq.getCantidad(), producto.getPrecio(), prodReq.getDescuento());
            detallesCompraRepository.save(detalles);
        }

        // Registrar pagos
        for (FacturaRequestDTO.MedioPagoRequestDTO pagoReq : request.getMediosPago()) {
            TipoPago tipoPago = tipoPagoRepository.findByNombre(pagoReq.getTipoPago()).get();
            Pago pago = new Pago(compra, tipoPago, pagoReq.getTipoTarjeta(), pagoReq.getCuotas(), pagoReq.getValor());
            pagoRepository.save(pago);
        }

        // Respuesta
        return new FacturaResponseDTO(compra.getId(), totalFactura, compra.getFecha());
    }

    private Cliente registrarCliente(FacturaRequestDTO.ClienteRequestDTO clienteReq) {
        Cliente cliente = new Cliente(clienteReq.getNombre(), clienteReq.getDocumento(), clienteReq.getTipoDocumento());
        return clienteRepository.save(cliente);
    }
    public ConsultarFacturaResponseDTO consultarFactura(ConsultarFacturaRequestDTO request, String tiendaUuid) {
        // Validar token del cajero
        Cajero cajero = cajeroRepository.findByToken(request.getToken())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Token no corresponde a ningún cajero en la tienda"));

        if (!cajero.getTienda().getUuid().equals(tiendaUuid)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "El cajero no está asignado a esta tienda");
        }

        // Validar existencia de la factura
        Compra factura = facturaRepository.findById(request.getFactura())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Factura no encontrada"));

        if (!factura.getCliente().getDocumento().equals(request.getCliente())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "El cliente no coincide con la factura solicitada");
        }

        // Construir la respuesta
        ConsultarFacturaResponseDTO response = new ConsultarFacturaResponseDTO();
        response.setTotal(factura.getTotal());
        response.setImpuestos(factura.getImpuestos());

        // Datos del cliente
        ConsultarFacturaResponseDTO.ClienteFacturaDTO clienteDTO = new ConsultarFacturaResponseDTO.ClienteFacturaDTO();
        clienteDTO.setDocumento(factura.getCliente().getDocumento());
        clienteDTO.setNombre(factura.getCliente().getNombre());
        clienteDTO.setTipoDocumento(factura.getCliente().getTipoDocumento().getNombre());
        response.setCliente(clienteDTO);

        // Datos de los productos
        List<ConsultarFacturaResponseDTO.ProductoFacturaDTO> productosDTO = factura.getDetallesCompra().stream().map(detalle -> {
            ConsultarFacturaResponseDTO.ProductoFacturaDTO productoDTO = new ConsultarFacturaResponseDTO.ProductoFacturaDTO();
            productoDTO.setReferencia(detalle.getProducto().getReferencia());
            productoDTO.setNombre(detalle.getProducto().getNombre());
            productoDTO.setCantidad(detalle.getCantidad());
            productoDTO.setPrecio(detalle.getProducto().getPrecio());
            productoDTO.setDescuento(detalle.getDescuento());
            productoDTO.setSubtotal(detalle.getPrecio().subtract(detalle.getDescuento()));
            return productoDTO;
        }).collect(Collectors.toList());
        response.setProductos(productosDTO);

        // Datos del cajero
        ConsultarFacturaResponseDTO.CajeroFacturaDTO cajeroDTO = new ConsultarFacturaResponseDTO.CajeroFacturaDTO();
        cajeroDTO.setDocumento(cajero.getDocumento());
        cajeroDTO.setNombre(cajero.getNombre());
        response.setCajero(cajeroDTO);

        return response;
    }
}
