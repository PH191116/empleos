package com.example.mpadilla.empleos.controller;

import com.example.mpadilla.empleos.Model.Vacante;
import com.example.mpadilla.empleos.service.ICategoriaService;
import com.example.mpadilla.empleos.service.IVacantesService;
import com.example.mpadilla.empleos.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/vacantes")
public class VacanteController {
    @Value("${empleosapp.ruta.imagenes}")
    private String ruta;
    @Autowired
    private IVacantesService serviceVacantes;
    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping("/create")
    public String crear(Vacante vacante, Model model){
        return "vacantes/formVacante";
    }
    @PostMapping("/save")
    public String guardar(@RequestParam("archivoImagen")MultipartFile multipartFile, Vacante vacante, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()){
            for (ObjectError error: result.getAllErrors()) {
                System.out.println("Ocurrio un error: "+error.getDefaultMessage());
            }
            return "vacantes/formVacante";
        }
        if (!multipartFile.isEmpty()){
            //String ruta
            //String ruta = "C:/empleos/img-vacantes/";
            String nombreImagen = Utileria.guardarArchivo(multipartFile, ruta);
            if (nombreImagen!=null){//si la imagen se subio
                //Procesamos la variable imagen
                vacante.setImagen(nombreImagen);
            }
        }
        System.out.println(vacante);
        serviceVacantes.guardar(vacante);
        attributes.addFlashAttribute("msg", "Registro guardado exitosamente");
        return "redirect:/vacantes/index";
    }
    @GetMapping("/index")
    public String mostrarIndex(Model model){
       List<Vacante> vacanteList = serviceVacantes.buscarAll();
        model.addAttribute("vacantes", vacanteList);
        return "vacantes/listVacantes";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int idVacante, Model model){
        Vacante vacante = serviceVacantes.findById(idVacante);
        model.addAttribute("vacante", vacante);
        return "vacantes/formVacante";
    }
    @ModelAttribute
    public void setGenericos(Model model){
        model.addAttribute("categorias", categoriaService.buscarTodas());
    }

//    @PostMapping("/save")
//    public String guardar(@RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion,
//                          @RequestParam("estatus") String status, @RequestParam("fecha") String fecha,
//                          @RequestParam("destacado") int destacado, @RequestParam("salario") double salario, @RequestParam("detalles") String detalles){
//        System.out.println("Nombre vacante: "+nombre);
//        System.out.println("Descripcion: "+descripcion);
//        System.out.println("Status: "+ status);
//        System.out.println("Fecha publicacion: "+fecha);
//        System.out.println("Destacado: "+destacado);
//        System.out.println("Salario ofrecido: "+salario);
//        System.out.println("Detalles: "+detalles);
//        return "vacantes/listVacantes";
//    }
    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idVacante, Model model, RedirectAttributes attributes){
        serviceVacantes.eliminarVacante(idVacante);
        attributes.addFlashAttribute("msg", "Vacante ha sido eliminada");
        System.out.println("Borrando vacante con id: "+idVacante);
        return "redirect:/vacantes/index";
    }

    @GetMapping("/view/{id}")
    public String verDetalle(@PathVariable("id") int idVacante, Model model){
        Vacante v = serviceVacantes.findById(idVacante);
        System.out.println("Vacante: "+v);
        model.addAttribute("vacante", v);

        //BUSCAR LOS DETALLES DE LA VACANTE EN LA BDD
        return "detalle";
    }
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
