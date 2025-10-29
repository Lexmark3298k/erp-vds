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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vdsolutions.erp.model.EstadoTecnico;
import com.vdsolutions.erp.model.Tecnico;
import com.vdsolutions.erp.service.TecnicoService;

@Controller
@RequestMapping("/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping
    public String listarTecnicos(Model model) {
        List<Tecnico> tecnicos = tecnicoService.obtenerTodosTecnicos();
        long disponibles = tecnicoService.contarTecnicosDisponibles();
        
        model.addAttribute("tecnicos", tecnicos);
        model.addAttribute("disponibles", disponibles);
        model.addAttribute("titulo", "Gestión de Técnicos");
        model.addAttribute("estadosTecnico", EstadoTecnico.values());
        return "tecnicos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("tecnico", new Tecnico());
        model.addAttribute("titulo", "Nuevo Técnico");
        model.addAttribute("estadosTecnico", EstadoTecnico.values());
        return "tecnicos/formulario";
    }

    @PostMapping("/guardar")
    public String guardarTecnico(@ModelAttribute Tecnico tecnico, 
                               RedirectAttributes redirectAttributes) {
        try {
            tecnicoService.guardarTecnico(tecnico);
            redirectAttributes.addFlashAttribute("success", "Técnico guardado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar técnico: " + e.getMessage());
        }
        return "redirect:/tecnicos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        return tecnicoService.obtenerTecnicoPorId(id)
                .map(tecnico -> {
                    model.addAttribute("tecnico", tecnico);
                    model.addAttribute("titulo", "Editar Técnico");
                    model.addAttribute("estadosTecnico", EstadoTecnico.values());
                    return "tecnicos/formulario";
                })
                .orElse("redirect:/tecnicos");
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarTecnico(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            tecnicoService.eliminarTecnico(id);
            redirectAttributes.addFlashAttribute("success", "Técnico eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar técnico: " + e.getMessage());
        }
        return "redirect:/tecnicos";
    }

    @GetMapping("/cambiar-estado/{id}/{estado}")
    public String cambiarEstado(@PathVariable Long id, @PathVariable EstadoTecnico estado,
                              RedirectAttributes redirectAttributes) {
        try {
            tecnicoService.cambiarEstadoTecnico(id, estado);
            redirectAttributes.addFlashAttribute("success", "Estado del técnico actualizado");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al cambiar estado: " + e.getMessage());
        }
        return "redirect:/tecnicos";
    }
}