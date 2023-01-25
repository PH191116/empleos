package com.example.mpadilla.empleos.controller;

import com.example.mpadilla.empleos.Model.Categoria;
import com.example.mpadilla.empleos.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value="/categorias")
public class CategoriasController {

    @Autowired
    private ICategoriaService categoriaService;

    // @GetMapping("/index")
    @RequestMapping(value="/index", method=RequestMethod.GET)
    public String mostrarIndex(Model model) {
        if (categoriaService.buscarTodas() != null){
            List<Categoria> categoriaList = categoriaService.buscarTodas();
            model.addAttribute("categorias", categoriaList);
        }
        return "categorias/listCategorias";
    }
    // @GetMapping("/create")
    @RequestMapping(value="/create", method=RequestMethod.GET)
    public String crear(Categoria categoria) {

        return "categorias/formCategoria";
    }
    @PostMapping("/save")
    //@RequestMapping(value="/save", method= RequestMethod.POST)
    public String guardar(Categoria categoria, BindingResult result, RedirectAttributes attributes) {
        //redireccionar al formulario en caso de error
        if (result.hasErrors()){
            for (ObjectError error: result.getAllErrors()){
                System.out.println("Ocurrio un error: "+error.getDefaultMessage());
            }
            return "categorias/formCategoria";
        }
        categoriaService.guardar(categoria);
        //Renderizar msj de exito en la vista en la lista
        //de categorias
        attributes.addFlashAttribute("msg", "Categoria guardada exitosamente");

        return "redirect:/categorias/index";
    }

}
