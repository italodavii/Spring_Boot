package com.example.estudo_patudos_api_spring_jpa.service;

import com.example.estudo_patudos_api_spring_jpa.dto.AnimalDTO;
import com.example.estudo_patudos_api_spring_jpa.model.Animal;
import com.example.estudo_patudos_api_spring_jpa.model.Instituicao;
import com.example.estudo_patudos_api_spring_jpa.repository.AnimalRepository;
import com.example.estudo_patudos_api_spring_jpa.repository.InstituicaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository repository;
    private final InstituicaoRepository instituicaoRepository;

    public AnimalService(AnimalRepository repository, InstituicaoRepository instituicaoRepository) {
        this.repository = repository;
        this.instituicaoRepository = instituicaoRepository;
    }

    public List<AnimalDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public AnimalDTO salvar(Animal animal) {
        if (animal.getInstituicao() != null && animal.getInstituicao().getId() != null) {
            Long idInst = animal.getInstituicao().getId();
            Instituicao inst = instituicaoRepository.findById(idInst)
                    .orElseThrow(() -> new RuntimeException("Instituição não encontrada!"));
            animal.setInstituicao(inst);
        }

        Animal salvo = repository.save(animal);
        return converterParaDTO(salvo);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    // Centralizamos a conversão aqui para evitar repetição de código
    private AnimalDTO converterParaDTO(Animal animal) {
        return new AnimalDTO(
                animal.getId(),
                animal.getNome(),
                animal.getEspecie(),
                animal.getIdade(),
                animal.getInstituicao() != null ? animal.getInstituicao().getNome() : "Sem Instituição"
        );
    }
}