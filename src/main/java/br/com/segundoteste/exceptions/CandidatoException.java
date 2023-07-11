package br.com.segundoteste.exceptions;

public class CandidatoException extends RuntimeException {
    public CandidatoException(String message) {
        super(message);
    }
    
    public static class BadRequestException extends CandidatoException {
        public BadRequestException() {
            super("Nome inválido.");
        }
    }
    
    public static class ConflictException extends CandidatoException {
        public ConflictException() {
            super("Candidato já participa do processo.");
        }
    }
    
    public static class NotFoundException extends CandidatoException {
        public NotFoundException() {
            super("Candidato não encontrado.");
        }
    }
}