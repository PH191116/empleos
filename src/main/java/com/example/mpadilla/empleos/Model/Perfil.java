package com.example.mpadilla.empleos.Model;

import javax.persistence.*;

@Entity
@Table(name="perfiles")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String perfil;

    public Perfil() {
    }

    public Perfil(Integer id, String perfil) {
        this.id = id;
        this.perfil = perfil;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
