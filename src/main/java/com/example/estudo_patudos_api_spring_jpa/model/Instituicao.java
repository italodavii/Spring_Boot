package com.example.estudo_patudos_api_spring_jpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cnpj;
    private String cidade;

    @OneToMany(mappedBy = "instituicao")
    @JsonIgnore
    private List<Animal> animais;
}
