package com.example.mpadilla.empleos.service;

import com.example.mpadilla.empleos.Model.Categoria;

import java.util.List;

public interface ICategoriaService {
    void guardar(Categoria categoria);
    List<Categoria> buscarTodas();
    Categoria buscarPorId(Integer idCategoria);
}
