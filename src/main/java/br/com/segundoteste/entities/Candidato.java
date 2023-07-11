package br.com.segundoteste.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Candidato {
    private int id;
    private String nome;
    private String status;
}
