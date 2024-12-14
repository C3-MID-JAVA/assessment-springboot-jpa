package es.cuenta_bancaria.mapper;

import es.cuenta_bancaria.dto.CuentaDTO;
import es.cuenta_bancaria.dto.TransaccionDTO;
import es.cuenta_bancaria.model.Cuenta;
import es.cuenta_bancaria.model.Transaccion;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DTOMapper {
    public CuentaDTO convertirACuentaDTO(Cuenta cuenta) {
        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setIdCuenta(cuenta.getIdCuenta());
        cuentaDTO.setTitular(cuenta.getTitular());
        cuentaDTO.setSaldo(cuenta.getSaldo());

        return cuentaDTO;
    }

    public TransaccionDTO convertirATransaccionDTO(Transaccion transaccion) {
        TransaccionDTO dto = new TransaccionDTO();
        dto.setIdTransaccion(transaccion.getIdTransaccion());
        dto.setMonto(transaccion.getMonto());
        dto.setTipo(transaccion.getTipo());
        dto.setCosto(transaccion.getCosto());
        dto.setCuenta(transaccion.getCuenta());
        return dto;
    }
}
