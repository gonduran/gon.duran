package com.example.pacientes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.pacientes.model.HistorialMedico;
import com.example.pacientes.repository.HistorialMedicoRepository;
import java.util.List;
import java.util.Optional;

@Service
public class HistorialMedicoServiceImpl implements HistorialMedicoService{
    @Autowired
    private HistorialMedicoRepository historialMedicoRepository;

    @Override
    public List<HistorialMedico> getAllHistorialMedicos() {
        return historialMedicoRepository.findAll();
    }

    @Override
    public Optional<HistorialMedico> getHistorialMedicoById(Long id) {
        return historialMedicoRepository.findById(id);
    }
    
    @Override
    public HistorialMedico createHistorialMedico(HistorialMedico historialMedico){
        return historialMedicoRepository.save(historialMedico);
    }

    @Override
    public HistorialMedico updateHistorialMedico(Long id, HistorialMedico historialMedico){
        if(historialMedicoRepository.existsById(id)){
            historialMedico.setId(id);
            return historialMedicoRepository.save(historialMedico);
        }   else {
                return null;
        }
    }

    @Override
    public void deleteHistorialMedico(Long id){
        historialMedicoRepository.deleteById(id);
    }

    @Override
    public List<HistorialMedico> getAllHistorialMedicosPacienteById(Long id){
        return historialMedicoRepository.findByPacienteId(id);
    }

}
