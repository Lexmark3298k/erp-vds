package com.vdsolutions.erp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vdsolutions.erp.model.Producto;
import com.vdsolutions.erp.repository.ProductoRepository;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerTodosProductos() {
        return productoRepository.findByActivoTrue();
    }

    public List<Producto> obtenerProductosBajoStock() {
        return productoRepository.findByStockLessThanEqualAndActivoTrue(5);
    }

    public List<Producto> obtenerProductosPorCategoria(String categoria) {
        return productoRepository.findByCategoriaAndActivoTrue(categoria);
    }

    public List<String> obtenerCategorias() {
        return productoRepository.findDistinctCategorias();
    }

    public Optional<Producto> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).filter(Producto::isActivo);
    }

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {
        productoRepository.findById(id).ifPresent(producto -> {
            producto.setActivo(false);
            productoRepository.save(producto);
        });
    }

    public void actualizarStock(Long id, Integer cantidad) {
        productoRepository.findById(id).ifPresent(producto -> {
            producto.setStock(producto.getStock() + cantidad);
            productoRepository.save(producto);
        });
    }

    public long contarProductos() {
        return productoRepository.countByActivoTrue();
    }

    public long contarProductosBajoStock() {
        return productoRepository.countByStockLessThanEqualAndActivoTrue(5);
    }
}