package es.cuenta_bancaria.controller;
import es.cuenta_bancaria.dto.CuentaDTO;
import es.cuenta_bancaria.service.ICuentaServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    @Autowired
    ICuentaServicio cuentaServicio;

    @GetMapping
    public ResponseEntity<List<CuentaDTO>> listarCuentas(){
        var response = cuentaServicio.listarCuentas();
        return response.isEmpty()?
                ResponseEntity.noContent().build():
                ResponseEntity.status(200).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<CuentaDTO> obtenerCuentaPorId(@PathVariable String id){
        var cuenta = cuentaServicio.obtenerCuentaPorId(id);
        return cuenta != null ?
                ResponseEntity.ok(cuenta):
                ResponseEntity.noContent().build();
    }


    @PostMapping
    public ResponseEntity<CuentaDTO> crearCuenta(@RequestBody @Valid CuentaDTO cuentaDTO){
        var response = cuentaServicio.crearCuenta(cuentaDTO);

        return response != null?
                ResponseEntity.status(201).body(response):
                ResponseEntity.status(304).build();
    }

    @PostMapping("{id}/transacciones")
    public ResponseEntity<CuentaDTO> realizarTransaccion(@Valid
            @PathVariable String id,
            @RequestParam BigDecimal monto,
            @RequestParam String tipo){
        var response = cuentaServicio.realizarTransaccion(id,monto,tipo);
        return ResponseEntity.ok(response);

    }

}
