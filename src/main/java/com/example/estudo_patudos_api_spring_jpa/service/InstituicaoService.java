package com.example.estudo_patudos_api_spring_jpa.service;

import com.example.estudo_patudos_api_spring_jpa.dto.InstituicaoDTO;
import com.example.estudo_patudos_api_spring_jpa.model.Instituicao;
import com.example.estudo_patudos_api_spring_jpa.repository.InstituicaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstituicaoService {
    private final InstituicaoRepository repository;
    public InstituicaoService(InstituicaoRepository repository) { this.repository = repository; }

    public List<InstituicaoDTO> listarTodas() {
        return repository.findAll().stream()
                .map(i -> new InstituicaoDTO(i.getNome(), i.getCidade())).toList();
    }

    public InstituicaoDTO salvar(Instituicao inst) {
        if (inst.getCnpj() == null) throw new RuntimeException("CNPJ é obrigatório");

        Instituicao salva = repository.save(inst);
        return new InstituicaoDTO(salva.getNome(), salva.getCidade());
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}