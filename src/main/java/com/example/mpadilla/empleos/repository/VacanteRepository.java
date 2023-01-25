package com.example.mpadilla.empleos.repository;

import com.example.mpadilla.empleos.Model.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacanteRepository extends JpaRepository<Vacante, Integer> {
    //nombre del parametro no necesariamente debe coincidir con el nombre
    //del atributo de la clase
    List<Vacante> findByEstatus(String estatus);
    List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(int destacado, String estatus);
    List<Vacante> findBySalarioBetweenOrderBySalarioDesc(double salary1, double salary2);
    List<Vacante> findByEstatusIn(String[] status);
}
