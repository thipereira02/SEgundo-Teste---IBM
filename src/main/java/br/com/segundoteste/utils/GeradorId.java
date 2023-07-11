package br.com.segundoteste.utils;

import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class GeradorId {
    public int gerarId(){
        UUID uuid = UUID.randomUUID();
        String idString = uuid.toString().replace("-", "");
        return Math.abs(idString.hashCode());
    }
}
