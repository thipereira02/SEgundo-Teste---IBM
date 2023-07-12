package br.com.segundoteste.repositories;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.ArrayList;
import br.com.segundoteste.entities.Candidato;

@Repository
public class CandidatoRepository {
    private List<Candidato> listaCandidatos = new ArrayList<>();

    public CandidatoRepository(){
        this.listaCandidatos = new ArrayList<>();
    }

    public Candidato buscarPorNome(String nome) {
        for (Candidato candidato : listaCandidatos) {
            if (candidato.getNome().equals(nome)) {
                return candidato;
            }
        }
        return null;
    }

    public Candidato buscarPorId(int codCandidato){
        for (Candidato candidato : listaCandidatos){
            if (candidato.getId() == codCandidato){
                return candidato;
            }
        }
        return null;
    }

    public void adicionarCandidato(Candidato candidato){
        listaCandidatos.add(candidato);
    }

    public void removerCandidato(Candidato candidato) {
        listaCandidatos.remove(candidato);
    }

    public List<Candidato> obterAprovados() {
        List<Candidato> aprovados = new ArrayList<>();
        for (Candidato candidato : listaCandidatos) {
            if (candidato.getStatus().equals("Aprovado")) {
                aprovados.add(candidato);
            }
        }
        return aprovados;
    }
}

