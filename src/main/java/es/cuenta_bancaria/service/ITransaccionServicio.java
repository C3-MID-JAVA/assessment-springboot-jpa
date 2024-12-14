package es.cuenta_bancaria.service;

import es.cuenta_bancaria.dto.TransaccionDTO;

import java.util.List;

public interface ITransaccionServicio {
    List<TransaccionDTO> listarTransacciones();
    TransaccionDTO buscarTransaccionPorId(String id);

}
