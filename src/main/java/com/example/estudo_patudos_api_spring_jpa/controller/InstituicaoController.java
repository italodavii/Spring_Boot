package com.example.estudo_patudos_api_spring_jpa.controller;

import com.example.estudo_patudos_api_spring_jpa.dto.InstituicaoDTO;
import com.example.estudo_patudos_api_spring_jpa.model.Instituicao;
import com.example.estudo_patudos_api_spring_jpa.service.InstituicaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instituicoes")
public class InstituicaoController {

    private final InstituicaoService service;

    public InstituicaoController(InstituicaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<InstituicaoDTO> listar() {
        return service.listarTodas();
    }

    @PostMapping
    public InstituicaoDTO cadastrar(@RequestBody Instituicao instituicao) {
        return service.salvar(instituicao);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
