package com.example.mpadilla.empleos.controller;

import com.example.mpadilla.empleos.Model.Vacante;
import com.example.mpadilla.empleos.service.IVacantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller //anotacion para SpringMVC
public class HomeController {

    @Autowired
    private IVacantesService serviceVacantes;
    @GetMapping("/tabla")
    public String mostrarTabla(Model modelo) throws ParseException {

        List<Vacante> lista = serviceVacantes.buscarAll();
        modelo.addAttribute("vacantes", lista);
        return "tabla";
    }
    @GetMapping("detalle")
    public String mostrarDetalle(Model modelo){
        Vacante vacante = new Vacante();
        vacante.setNombre("Ingeniero de comunicaciones");
        vacante.setDescripcion("Se solicita ingeniero para dar soporte a " +
                "intranet");
        vacante.setFecha(new Date());
        vacante.setSalario(800.0);
        modelo.addAttribute("vacante", vacante);
        return "detalle";
    }

    @GetMapping("listado")
    public String mostrarListado(Model model){
        List<String> lista = new LinkedList<String>();
        lista.add("Ingeniero de sistemas");
        lista.add("Auxiliar de contabilidad");
        lista.add("Vendedor");
        lista.add("Arquiecto");
        model.addAttribute("empleos", lista);
        return "listado";
    }

    @GetMapping("/")//URL A AL QUE RESPONDERA EL METODO
    public String mostrarHome(Model modelo){//variable que sirve para agregar datos en el controller
        List<Vacante> lista = serviceVacantes.buscarDestacadas();
        modelo.addAttribute("vacantes", lista);
        return "home";
    }

    /*public List<Vacante> getVacantes() throws ParseException {

    }*/
}
