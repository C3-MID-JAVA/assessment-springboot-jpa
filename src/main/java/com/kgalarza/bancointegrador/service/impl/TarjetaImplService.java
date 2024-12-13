package com.kgalarza.bancointegrador.service.impl;

import com.kgalarza.bancointegrador.mapper.TarjetaMapper;
import com.kgalarza.bancointegrador.model.dto.TarjetaInDto;
import com.kgalarza.bancointegrador.model.dto.TarjetaOutDto;
import com.kgalarza.bancointegrador.model.entity.Cuenta;
import com.kgalarza.bancointegrador.model.entity.Tarjeta;
import com.kgalarza.bancointegrador.repository.CuentaRepository;
import com.kgalarza.bancointegrador.repository.TarjetaRepository;
import com.kgalarza.bancointegrador.service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.List;

@Service
public class TarjetaImplService implements TarjetaService {
    private final TarjetaRepository tarjetaRepository;
    private final CuentaRepository cuentaRepository;
    private final TarjetaMapper tarjetaMapper;

    @Autowired
    public TarjetaImplService(TarjetaRepository tarjetaRepository,
                              CuentaRepository cuentaRepository,
                              TarjetaMapper tarjetaMapper) {
        this.tarjetaRepository = tarjetaRepository;
        this.cuentaRepository = cuentaRepository;
        this.tarjetaMapper = tarjetaMapper;
    }

    @Override
    public TarjetaOutDto crearTarjeta(TarjetaInDto tarjetaInDto) {
        Cuenta cuenta = cuentaRepository.findById(tarjetaInDto.getCuentaId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada con ID: " + tarjetaInDto.getCuentaId()));

        Tarjeta tarjeta = tarjetaMapper.mapToEntity(tarjetaInDto, cuenta);
        Tarjeta tarjetaGuardada = tarjetaRepository.save(tarjeta);
        return tarjetaMapper.mapToDto(tarjetaGuardada);
    }

    @Override
    public List<TarjetaOutDto> obtenerTodasLasTarjetas() {
        return tarjetaRepository.findAll().stream()
                .map(tarjetaMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TarjetaOutDto obtenerTarjetaPorId(Long id) {
        Tarjeta tarjeta = tarjetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarjeta no encontrada con ID: " + id));
        return tarjetaMapper.mapToDto(tarjeta);
    }

    @Override
    public TarjetaOutDto actualizarTarjeta(Long id, TarjetaInDto tarjetaInDto) {
        Tarjeta tarjeta = tarjetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarjeta no encontrada con ID: " + id));

        Cuenta cuenta = cuentaRepository.findById(tarjetaInDto.getCuentaId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada con ID: " + tarjetaInDto.getCuentaId()));

        tarjeta.setNumeroTarjeta(tarjetaInDto.getNumeroTarjeta());
        tarjeta.setTipo(tarjetaInDto.getTipo());
        tarjeta.setCuenta(cuenta);

        Tarjeta tarjetaActualizada = tarjetaRepository.save(tarjeta);
        return tarjetaMapper.mapToDto(tarjetaActualizada);
    }

    @Override
    public void eliminarTarjeta(Long id) {
        if (!tarjetaRepository.existsById(id)) {
            throw new RuntimeException("Tarjeta no encontrada con ID: " + id);
        }
        tarjetaRepository.deleteById(id);
    }
}
