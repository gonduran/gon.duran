package com.example.pacientes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pacientes.model.Paciente;
import com.example.pacientes.repository.PacienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService{
    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    @Override
    public Optional<Paciente> getPacienteById(Long id) {
        return pacienteRepository.findById(id);
    }
    
    @Override
    public Paciente createPaciente(Paciente usuario){
        return pacienteRepository.save(usuario);
    }

    @Override
    public Paciente updatePaciente(Long id, Paciente paciente){
        if(pacienteRepository.existsById(id)){
            paciente.setId(id);
            return pacienteRepository.save(paciente);
        }   else {
                return null;
        }
    }

    @Override
    public void deletePaciente(Long id){
        pacienteRepository.deleteById(id);
    }
}
