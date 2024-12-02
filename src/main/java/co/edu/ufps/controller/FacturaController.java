package co.edu.ufps.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.entityDTO.FacturaRequestDTO;
import co.edu.ufps.entityDTO.FacturaResponseDTO;
import co.edu.ufps.exception.CustomException;
import co.edu.ufps.service.FacturaService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

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
            FacturaResponseDTO response = facturaService.crearFactura(tiendaUuid, request);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(Map.of("status", "error", "message", e.getMessage(), "data", null));
        }
    }
}