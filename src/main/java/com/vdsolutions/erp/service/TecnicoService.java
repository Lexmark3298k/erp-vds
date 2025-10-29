package com.vdsolutions.erp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vdsolutions.erp.model.EstadoTecnico;
import com.vdsolutions.erp.model.Tecnico;
import com.vdsolutions.erp.repository.TecnicoRepository;

@Service
@Transactional
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public List<Tecnico> obtenerTodosTecnicos() {
        return tecnicoRepository.findByActivoTrue();
    }

    public List<Tecnico> obtenerTecnicosDisponibles() {
        return tecnicoRepository.findByEstadoAndActivoTrue(EstadoTecnico.DISPONIBLE);
    }

    public Optional<Tecnico> obtenerTecnicoPorId(Long id) {
        return tecnicoRepository.findById(id).filter(Tecnico::isActivo);
    }

    public Tecnico guardarTecnico(Tecnico tecnico) {
        return tecnicoRepository.save(tecnico);
    }

    public void eliminarTecnico(Long id) {
        tecnicoRepository.findById(id).ifPresent(tecnico -> {
            tecnico.setActivo(false);
            tecnicoRepository.save(tecnico);
        });
    }

    public void cambiarEstadoTecnico(Long id, EstadoTecnico estado) {
        tecnicoRepository.findById(id).ifPresent(tecnico -> {
            tecnico.setEstado(estado);
            tecnicoRepository.save(tecnico);
        });
    }

    public long contarTecnicosDisponibles() {
        return tecnicoRepository.countByEstadoAndActivoTrue(EstadoTecnico.DISPONIBLE);
    }
}