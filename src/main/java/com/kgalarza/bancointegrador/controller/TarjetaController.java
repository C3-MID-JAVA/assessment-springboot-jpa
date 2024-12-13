package com.kgalarza.bancointegrador.controller;

import com.kgalarza.bancointegrador.model.dto.TarjetaInDto;
import com.kgalarza.bancointegrador.model.dto.TarjetaOutDto;
import com.kgalarza.bancointegrador.service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/tarjetas")
public class TarjetaController {

    private final TarjetaService tarjetaService;

    @Autowired
    public TarjetaController(TarjetaService tarjetaService) {
        this.tarjetaService = tarjetaService;
    }

    @PostMapping
    public ResponseEntity<TarjetaOutDto> crearTarjeta(@RequestBody TarjetaInDto tarjetaInDto) {
        TarjetaOutDto nuevaTarjeta = tarjetaService.crearTarjeta(tarjetaInDto);
        return ResponseEntity.ok(nuevaTarjeta);
    }

    @GetMapping
    public ResponseEntity<List<TarjetaOutDto>> obtenerTodasLasTarjetas() {
        List<TarjetaOutDto> tarjetas = tarjetaService.obtenerTodasLasTarjetas();
        return ResponseEntity.ok(tarjetas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarjetaOutDto> obtenerTarjetaPorId(@PathVariable Long id) {
        try {
            TarjetaOutDto tarjeta = tarjetaService.obtenerTarjetaPorId(id);
            return ResponseEntity.ok(tarjeta);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarjetaOutDto> actualizarTarjeta(@PathVariable Long id, @RequestBody TarjetaInDto tarjetaInDto) {
        try {
            TarjetaOutDto tarjetaActualizada = tarjetaService.actualizarTarjeta(id, tarjetaInDto);
            return ResponseEntity.ok(tarjetaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una tarjeta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarjeta(@PathVariable Long id) {
        try {
            tarjetaService.eliminarTarjeta(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
