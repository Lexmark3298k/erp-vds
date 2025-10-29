package com.vdsolutions.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.vdsolutions.erp.service.ClienteService;
import com.vdsolutions.erp.service.ProductoService;
import com.vdsolutions.erp.service.TecnicoService;

@Controller
public class DashboardController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ProductoService productoService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Obtener estadísticas
        long totalClientes = clienteService.obtenerTodosClientes().size();
        long totalTecnicos = tecnicoService.obtenerTodosTecnicos().size();
        long totalProductos = productoService.contarProductos();
        long productosBajoStock = productoService.contarProductosBajoStock();
        
        model.addAttribute("titulo", "Dashboard");
        model.addAttribute("totalClientes", totalClientes);
        model.addAttribute("totalTecnicos", totalTecnicos);
        model.addAttribute("totalProductos", totalProductos);
        model.addAttribute("productosBajoStock", productosBajoStock);
        
        return "dashboard";
    }
}