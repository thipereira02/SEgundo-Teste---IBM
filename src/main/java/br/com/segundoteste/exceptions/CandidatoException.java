package br.com.segundoteste.exceptions;

public class CandidatoException {
    public static class NomeInvalidoException extends Exception {
        public NomeInvalidoException() {
            super("Nome inválido.");
        }
    }

    public static class CandidatoExistenteException extends Exception {
        public CandidatoExistenteException() {
            super("Candidato já participa do processo.");
        }
    }

    public static class CandidatoNaoEncontradoException extends Exception {
        public CandidatoNaoEncontradoException() {
            super("Candidato não encontrado.");
        }
    }
}
