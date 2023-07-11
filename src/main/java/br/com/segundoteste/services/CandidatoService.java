package br.com.segundoteste.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import br.com.segundoteste.utils.GeradorId;
import br.com.segundoteste.entities.Candidato;
import br.com.segundoteste.exceptions.CandidatoException;

@Service
public class CandidatoService {
    private List<Candidato> listaCandidatos = new ArrayList<Candidato>();
    private GeradorId geradorId;

    public CandidatoService(GeradorId geradorId) {
        this.geradorId = geradorId;
    }

    public int iniciarProcesso(String nome) throws CandidatoException.BadRequestException, CandidatoException.ConflictException {
    if (nome == null || nome.isEmpty() || !nome.matches("[a-zA-Z ]+")) {
        throw new CandidatoException.BadRequestException();
    }

    for (Candidato candidato : listaCandidatos) {
        if (candidato.getNome().equals(nome)) {
            throw new CandidatoException.ConflictException();
        }
    }

    int id = geradorId.gerarId();
    Candidato candidato = new Candidato(id, nome, "Recebido");
    listaCandidatos.add(candidato);
    return id;
}

    public void marcarEntrevista(int codCandidato) throws CandidatoException.NotFoundException{
        Candidato candidato = buscarPorId(codCandidato);

        if (candidato != null){
            if (candidato.getStatus().equals("Recebido")){
                candidato.setStatus("Qualificado");
            } else {
                throw new CandidatoException.NotFoundException();
            }
        } else {
            throw new CandidatoException.NotFoundException();
        }
    }

    public Candidato buscarPorId(int codCandidato){
        for (Candidato candidato : listaCandidatos){
            if (candidato.getId() == codCandidato){
                return candidato;
            }
        }

        return null;
    }

    public void desqualificarCandidato(int codCandidato) throws CandidatoException.NotFoundException{
        Candidato candidato = buscarPorId(codCandidato);

        if (candidato != null) {
            listaCandidatos.remove(candidato);
        } else {
            throw new CandidatoException.NotFoundException();
        }
    }

    public String verificarStatusCandidato(int codCandidato) throws CandidatoException.NotFoundException{
        Candidato candidato = buscarPorId(codCandidato);

        if (candidato != null) {
            return candidato.getStatus();
        } else {
            throw new CandidatoException.NotFoundException();
        }
    }

    public void aprovarCandidato(int codCandidato) throws CandidatoException.NotFoundException{
        Candidato candidato = buscarPorId(codCandidato);

        if (candidato != null) {
            if (candidato.getStatus().equals("Qualificado")){
                candidato.setStatus("Aprovado");
            } else {
                throw new CandidatoException.NotFoundException();
            }
        } else {
            throw new CandidatoException.NotFoundException();
        }
    }

    public List<Candidato> obterAprovados(){
        List<Candidato> aprovados = new ArrayList<Candidato>();
        for (Candidato candidato : listaCandidatos){
            if (candidato.getStatus().equals("Aprovado")){
                aprovados.add(candidato);
            }
        }

        return aprovados;
    }
}