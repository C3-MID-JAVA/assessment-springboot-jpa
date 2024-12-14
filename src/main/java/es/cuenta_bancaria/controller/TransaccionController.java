package es.cuenta_bancaria.controller;

import es.cuenta_bancaria.dto.TransaccionDTO;
import es.cuenta_bancaria.service.ITransaccionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {

    @Autowired
    ITransaccionServicio transaccionServicio;


    @GetMapping
    public ResponseEntity<List<TransaccionDTO>> listarTransacciones(){
        var response = transaccionServicio.listarTransacciones();
        return response.isEmpty()?
                ResponseEntity.noContent().build():
                ResponseEntity.ok().body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<TransaccionDTO> obtenerTransaccionPorId(@PathVariable String id){
        var response = transaccionServicio.buscarTransaccionPorId(id);
        return response != null?
                ResponseEntity.ok().body(response):
                ResponseEntity.noContent().build();
    }

}
