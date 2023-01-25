package com.example.mpadilla.empleos.service.impl;

import com.example.mpadilla.empleos.Model.Categoria;
import com.example.mpadilla.empleos.repository.CategoriasRepository;
import com.example.mpadilla.empleos.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements ICategoriaService {
    @Autowired
    private CategoriasRepository categoriasRepository;

    @Override
    public void guardar(Categoria categoria) {
    categoriasRepository.save(categoria);
    }

    @Override
    public List<Categoria> buscarTodas() {
        return categoriasRepository.findAll();
    }

    @Override
    public Categoria buscarPorId(Integer idCategoria) {
        Optional<Categoria> categoriaOptional = categoriasRepository.findById(idCategoria);
        if (categoriaOptional.isPresent())
            return categoriaOptional.get();
        return null;
    }
}
