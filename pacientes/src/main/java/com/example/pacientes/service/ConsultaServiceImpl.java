package com.example.pacientes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pacientes.model.Consulta;
import com.example.pacientes.repository.ConsultaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaServiceImpl  implements ConsultaService{
    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public List<Consulta> getAllConsultas() {
        return consultaRepository.findAll();
    }

    @Override
    public Optional<Consulta> getConsultaById(Long id) {
        return consultaRepository.findById(id);
    }
    
    @Override
    public Consulta createConsulta(Consulta consulta){
        return consultaRepository.save(consulta);
    }

    @Override
    public Consulta updateConsulta(Long id, Consulta consulta){
        if(consultaRepository.existsById(id)){
            consulta.setId(id);
            return consultaRepository.save(consulta);
        }   else {
                return null;
        }
    }

    @Override
    public void deleteConsulta(Long id){
        consultaRepository.deleteById(id);
    }

    @Override
    public List<Consulta> getAllConsultasPacienteById(Long id){
        return consultaRepository.findByPacienteId(id);
    }

}
