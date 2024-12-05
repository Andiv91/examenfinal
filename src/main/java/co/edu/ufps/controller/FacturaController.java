package co.edu.ufps.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.entityDTO.FacturaRequestDTO;
import co.edu.ufps.entityDTO.FacturaResponseDTO;
import co.edu.ufps.exception.CustomException;
import co.edu.ufps.service.FacturaService;

@RestController
@RequestMapping("/facturas")
public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @PostMapping("/crear/{tiendaUuid}")
    public ResponseEntity<?> crearFactura(@PathVariable String tiendaUuid, @RequestBody FacturaRequestDTO request) {
        try {
            // Llamada al servicio para crear la factura
            FacturaResponseDTO response = facturaService.crearFactura(tiendaUuid, request);
            
            // Construcción de la respuesta en el formato requerido
            return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "La factura se ha creado correctamente con el número: " + response.getData().getNumero(),
                "data", Map.of(
                    "numero", response.getData().getNumero(),
                    "total", response.getData().getTotal(),
                    "fecha", response.getData().getFecha()
                )
            ));
        } catch (CustomException e) {
            // En caso de error, retorno del error con el formato solicitado
            return ResponseEntity.status(e.getStatusCode())
                    .body(Map.of("status", "error", "message", e.getMessage(), "data", null));
        }
    }
}
