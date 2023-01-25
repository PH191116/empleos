package com.example.mpadilla.empleos.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String nombre;
    private String email;
    private String password;
    private Integer estatus;
    private Date fechaRegistro;
    @ManyToMany(fetch = FetchType.EAGER)//SIRVE PARA ESPECIFICAR QUE SELECCIONE TODOS LOS PERFILES QUE CORRESPONDEN A UN USUARIO
    //EN UNA SOLA CONSULTA
    @JoinTable(name = "usuarioperfil",
            joinColumns = @JoinColumn(name = "idUsuario"), //primero debe ir el nombre del id de la tabla que contiene la anotacion many To many
            inverseJoinColumns = @JoinColumn(name = "idPerfil")//Luego debe ir la de la otra tabla
    )//Tabla intermedia
    private List<Perfil> perfiles;

    //metodo Helper para guardar una lista de perfiles de tal forma
    //que al agregar un usuario de insertara el perfil en la BDD tambien
    public void agregar(Perfil tempPerfil){
        if(perfiles == null){
            perfiles  = new LinkedList<Perfil>();
        }
        perfiles.add(tempPerfil);
    }

    public Usuario() {
    }

    public Usuario(Integer id, String username, String nombre, String email, String password, Integer status, Date fechaRegistro) {
        this.id = id;
        this.username = username;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.estatus = status;
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return estatus;
    }

    public void setStatus(Integer status) {
        this.estatus = status;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }
}
