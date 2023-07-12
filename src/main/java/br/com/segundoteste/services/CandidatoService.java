package br.com.segundoteste.services;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import java.util.List;

import br.com.segundoteste.utils.GeradorId;
import br.com.segundoteste.entities.Candidato;
import br.com.segundoteste.exceptions.CandidatoException;
import br.com.segundoteste.repositories.CandidatoRepository;

@Service
public class CandidatoService {
    private List<Candidato> listaCandidatos = new ArrayList<Candidato>();
    private GeradorId geradorId;
    private CandidatoRepository candidatoRepository;

    public CandidatoService(GeradorId geradorId, CandidatoRepository candidatoRepository) {
        this.geradorId = geradorId;
        this.candidatoRepository = candidatoRepository;
    }

    public int iniciarProcesso(String nome) throws CandidatoException.BadRequestException, CandidatoException.ConflictException {
        if (nome == null || nome.isEmpty() || !nome.matches("[\\p{L}\\s]+")) {
            throw new CandidatoException.BadRequestException();
        }

        Candidato candidatoExistente = candidatoRepository.buscarPorNome(nome);
        if (candidatoExistente != null) {
            throw new CandidatoException.ConflictException();
        }


        int id = geradorId.gerarId();
        Candidato candidato = new Candidato(id, nome, "Recebido");
        candidatoRepository.adicionarCandidato(candidato);
        return id;
    }

    public void marcarEntrevista(int codCandidato) throws CandidatoException.NotFoundException{
        Candidato candidato = candidatoRepository.buscarPorId(codCandidato);

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

    public void desqualificarCandidato(int codCandidato) throws CandidatoException.NotFoundException{
        Candidato candidato = candidatoRepository.buscarPorId(codCandidato);

        if (candidato != null) {
            listaCandidatos.remove(candidato);
        } else {
            throw new CandidatoException.NotFoundException();
        }
    }

    public String verificarStatusCandidato(int codCandidato) throws CandidatoException.NotFoundException{
        Candidato candidato = candidatoRepository.buscarPorId(codCandidato);

        if (candidato != null) {
            return candidato.getStatus();
        } else {
            throw new CandidatoException.NotFoundException();
        }
    }

    public void aprovarCandidato(int codCandidato) throws CandidatoException.NotFoundException{
        Candidato candidato = candidatoRepository.buscarPorId(codCandidato);

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

    public List<Candidato> obterAprovados() {
        return candidatoRepository.obterAprovados();
    }
}