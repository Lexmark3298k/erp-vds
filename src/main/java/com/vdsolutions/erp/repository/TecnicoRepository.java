package com.vdsolutions.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vdsolutions.erp.model.EstadoTecnico;
import com.vdsolutions.erp.model.Tecnico;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {
    
    List<Tecnico> findByActivoTrue();
    List<Tecnico> findByEstadoAndActivoTrue(EstadoTecnico estado);
    
    @Query("SELECT t FROM Tecnico t WHERE t.nombre LIKE %:nombre% AND t.activo = true")
    List<Tecnico> buscarPorNombre(String nombre);
    
    long countByEstadoAndActivoTrue(EstadoTecnico estado);
}