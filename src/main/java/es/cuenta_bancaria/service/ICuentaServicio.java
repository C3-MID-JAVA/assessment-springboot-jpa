package es.cuenta_bancaria.service;

import es.cuenta_bancaria.dto.CuentaDTO;
import es.cuenta_bancaria.model.Cuenta;

import java.math.BigDecimal;
import java.util.List;

public interface ICuentaServicio {
    //Metodos que se van implementar , necesarios para la solucion
    List<CuentaDTO> listarCuentas();
    CuentaDTO crearCuenta(CuentaDTO cuentaDTO);
    CuentaDTO obtenerCuentaPorId(String id);
    CuentaDTO realizarTransaccion(String cuentaId, BigDecimal monto, String tipo);
}
