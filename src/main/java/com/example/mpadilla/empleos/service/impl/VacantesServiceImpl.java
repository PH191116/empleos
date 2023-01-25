package com.example.mpadilla.empleos.service.impl;

import com.example.mpadilla.empleos.Model.Vacante;
import com.example.mpadilla.empleos.repository.VacanteRepository;
import com.example.mpadilla.empleos.service.IVacantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class VacantesServiceImpl implements IVacantesService {
    @Autowired
    private VacanteRepository vacanteRepository;

    @Override
    public List<Vacante> buscarAll() {
        return vacanteRepository.findAll();
    }

    @Override
    public Vacante findById(int idVacante) {
        Optional<Vacante> vacanteOptional = vacanteRepository.findById(idVacante);
        if (vacanteOptional.isPresent())
            return vacanteOptional.get();
        return null;
    }

    @Override
    public void guardar(Vacante vacante) {
        vacanteRepository.save(vacante);

    }

    @Override
    public List<Vacante> buscarDestacadas() {
        return vacanteRepository.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobada");
    }

    @Override
    public void eliminarVacante(int idVacante) {
        vacanteRepository.deleteById(idVacante);
    }
}
