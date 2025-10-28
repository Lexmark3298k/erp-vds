package com.vdsolutions.erp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vdsolutions.erp.model.Producto;
import com.vdsolutions.erp.service.ProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String listarProductos(Model model) {
        List<Producto> productos = productoService.obtenerTodosProductos();
        List<Producto> productosBajoStock = productoService.obtenerProductosBajoStock();
        List<String> categorias = productoService.obtenerCategorias();
        
        model.addAttribute("productos", productos);
        model.addAttribute("productosBajoStock", productosBajoStock);
        model.addAttribute("categorias", categorias);
        model.addAttribute("titulo", "Gestión de Inventario");
        return "productos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("titulo", "Nuevo Producto");
        model.addAttribute("categorias", productoService.obtenerCategorias());
        return "productos/formulario";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto, 
                                RedirectAttributes redirectAttributes) {
        try {
            productoService.guardarProducto(producto);
            redirectAttributes.addFlashAttribute("success", "Producto guardado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar producto: " + e.getMessage());
        }
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        return productoService.obtenerProductoPorId(id)
                .map(producto -> {
                    model.addAttribute("producto", producto);
                    model.addAttribute("titulo", "Editar Producto");
                    model.addAttribute("categorias", productoService.obtenerCategorias());
                    return "productos/formulario";
                })
                .orElse("redirect:/productos");
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            productoService.eliminarProducto(id);
            redirectAttributes.addFlashAttribute("success", "Producto eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar producto: " + e.getMessage());
        }
        return "redirect:/productos";
    }

    @PostMapping("/actualizar-stock/{id}")
    public String actualizarStock(@PathVariable Long id, 
                                @RequestParam Integer cantidad,
                                RedirectAttributes redirectAttributes) {
        try {
            productoService.actualizarStock(id, cantidad);
            redirectAttributes.addFlashAttribute("success", "Stock actualizado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar stock: " + e.getMessage());
        }
        return "redirect:/productos";
    }
}