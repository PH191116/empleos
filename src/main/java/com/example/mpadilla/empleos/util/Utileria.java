package com.example.mpadilla.empleos.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class Utileria {

    public static String guardarArchivo(MultipartFile multipartFile, String ruta){
        //Obtenemos el nombre original del archivo
        String nombreOriginal = multipartFile.getOriginalFilename();
        //Agregar characteres al nombre del archivo para que no hayan registros repetidos
        String nombreFinal = randomAlphaNumeric(8)+nombreOriginal;
        nombreOriginal.replace(" ", "-");
        try {
            //Formamos el nombre del archivo para guardar en el disco duro
            File imageFile = new File(ruta+nombreFinal);
            System.out.println("Ruta archivo: "+imageFile.getAbsolutePath());
            //Guardamos fisicamente el archivo en HD
            multipartFile.transferTo(imageFile);
            return nombreFinal;
        }catch (IOException e){
            System.out.println("Error: "+e.getMessage());
            return null;
        }
    }
    /**
     * Metodo para generar una cadena aleatoria de longitud N
     * @Param count
     */
    public static String randomAlphaNumeric(int count){
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0){
            int character = (int) (Math.random()*caracteres.length());
            builder.append(caracteres.charAt(character));

        }
        return builder.toString();
    }

}
