package com.example.mpadilla.empleos.service;

import com.example.mpadilla.empleos.Model.Vacante;

import java.util.List;

public interface IVacantesService {
List<Vacante> buscarAll();
Vacante findById(int idVacante);
void guardar(Vacante vacante);
List<Vacante> buscarDestacadas();
void eliminarVacante(int idVacante);
}
