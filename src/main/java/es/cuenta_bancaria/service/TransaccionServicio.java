package es.cuenta_bancaria.service;

import es.cuenta_bancaria.dto.TransaccionDTO;
import es.cuenta_bancaria.mapper.DTOMapper;
import es.cuenta_bancaria.repository.TransaccionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TransaccionServicio implements ITransaccionServicio{
    @Autowired
    private TransaccionRepositorio transaccionRepositorio;

    @Autowired
    private DTOMapper dtoMapper;

    @Override
    public List<TransaccionDTO> listarTransacciones() {
        var response = transaccionRepositorio.findAll();
        return response
                .stream()
                .map(dtoMapper::convertirATransaccionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TransaccionDTO buscarTransaccionPorId(String id) {
        var response = transaccionRepositorio.findById(id).orElse(null);
        if(response != null){
            return dtoMapper.convertirATransaccionDTO(response);
        }
        return null;
    }


}
