package br.com.segundoteste.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.segundoteste.entities.Candidato;
import br.com.segundoteste.exceptions.CandidatoException;

public class CandidatoService {
    private List<Candidato> listaCandidatos = new ArrayList<Candidato>();

    public int iniciarProcesso(String nome) throws CandidatoException.NomeInvalidoException, CandidatoException.CandidatoExistenteException, CandidatoException.CandidatoNaoEncontradoException{
        if (nome == null || nome.isEmpty()) {
            throw new CandidatoException.NomeInvalidoException();
        }

        for (Candidato candidato : listaCandidatos) {
            if (candidato.getNome().equals(nome)) {
                throw new CandidatoException.CandidatoExistenteException();
            }
        }

        int id = gerarId();
        Candidato candidato = new Candidato(id, nome, "Recebido");
        listaCandidatos.add(candidato);
        return id;
    }

    private int gerarId(){
        UUID uuid = UUID.randomUUID();
        String idString = uuid.toString().replace("-", "");
        return Math.abs(idString.hashCode());
    }

    public void marcarEntrevista(int codCandidato) throws CandidatoException.CandidatoNaoEncontradoException{
        Candidato candidato = buscarPorId(codCandidato);

        if (candidato != null){
            if (candidato.getStatus().equals("Recebido")){
                candidato.setStatus("Qualificado");
            } else {
                throw new CandidatoException.CandidatoNaoEncontradoException();
            }
        } else {
            throw new CandidatoException.CandidatoNaoEncontradoException();
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

    public void desqualificarCandidato(int codCandidato) throws CandidatoException.CandidatoNaoEncontradoException{
        Candidato candidato = buscarPorId(codCandidato);

        if (candidato != null) {
            listaCandidatos.remove(candidato);
        } else {
            throw new CandidatoException.CandidatoNaoEncontradoException();
        }
    }

    public String verificarStatusCandidato(int codCandidato) throws CandidatoException.CandidatoNaoEncontradoException{
        Candidato candidato = buscarPorId(codCandidato);

        if (candidato != null) {
            return candidato.getStatus();
        } else {
            throw new CandidatoException.CandidatoNaoEncontradoException();
        }
    }

    public void aprovarCandidato(int codCandidato) throws CandidatoException.CandidatoNaoEncontradoException{
        Candidato candidato = buscarPorId(codCandidato);

        if (candidato != null) {
            if (candidato.getStatus().equals("Qualificado")){
                candidato.setStatus("Aprovado");
            } else {
                throw new CandidatoException.CandidatoNaoEncontradoException();
            }
        } else {
            throw new CandidatoException.CandidatoNaoEncontradoException();
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