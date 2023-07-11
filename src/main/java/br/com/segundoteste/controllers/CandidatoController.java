package br.com.segundoteste.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;

import br.com.segundoteste.exceptions.CandidatoException;
import br.com.segundoteste.services.CandidatoService;

@RestController
@RequestMapping("/api/v1/hiring")
public class CandidatoController {
    
    private CandidatoService candidatoService;

    public CandidatoController(CandidatoService candidatoService) {
        this.candidatoService = candidatoService;
    }

    @PostMapping("/start")
    public int iniciarProcesso(@RequestBody Map<String, String> requestBody) throws CandidatoException {
        String nome = requestBody.get("nome");
        return candidatoService.iniciarProcesso(nome);
    }

    @PostMapping("/schedule")
    public void marcarEntrevista(@RequestBody Map<String, Integer> requestBody) throws CandidatoException {
        int codCandidato = requestBody.get("codCandidato");
        candidatoService.marcarEntrevista(codCandidato);
    }

    @PostMapping("/disqualify")
    public void desqualificarCandidato(@RequestBody Map<String, Integer> requestBody) throws CandidatoException {
        int codCandidato = requestBody.get("codCandidato");
        candidatoService.desqualificarCandidato(codCandidato);
    }

    @PostMapping("/approve")
    public void aprovarCandidato(@RequestBody Map<String, Integer> requestBody) throws CandidatoException {
        int codCandidato = requestBody.get("codCandidato");
        candidatoService.aprovarCandidato(codCandidato);
    }
}
