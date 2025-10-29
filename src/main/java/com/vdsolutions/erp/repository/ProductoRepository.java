package com.vdsolutions.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vdsolutions.erp.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    List<Producto> findByActivoTrue();
    List<Producto> findByCategoriaAndActivoTrue(String categoria);
    List<Producto> findByStockLessThanEqualAndActivoTrue(Integer stockMinimo);
    
    @Query("SELECT p FROM Producto p WHERE p.nombre LIKE %:nombre% AND p.activo = true")
    List<Producto> buscarPorNombre(String nombre);
    
    @Query("SELECT DISTINCT p.categoria FROM Producto p WHERE p.activo = true")
    List<String> findDistinctCategorias();
    
    long countByActivoTrue();
    long countByStockLessThanEqualAndActivoTrue(Integer stockMinimo);
}