package com.example.mpadilla.empleos.repository;

import com.example.mpadilla.empleos.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
