package com.stefanini.taskManager_backend.infraestructure.client;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Component
public class AdviceClient {
    private final WebClient webClient;

    public AdviceClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public String buscarConselhoMotivacional() {

            // Chamada síncrona para simplificar o fluxo inicial, mas usando o motor do WebClient
        return webClient.get()
                .uri("/advice")
                .retrieve()
                .bodyToMono(AdviceResponse.class)
                .timeout(Duration.ofSeconds(3)) // Se demorar mais de 3s, cancela
                .map(response -> response.getSlip().getAdvice())
                .onErrorReturn("Foco no objetivo e o sucesso virá!") // Se der QUALQUER erro, retorna essa frase
                .block(); // Como estamos em MVC, bloqueamos para obter o resultado
    }

    @Data
    static class AdviceResponse {
        private Slip slip;
        @Data static class Slip { private String advice; }
    }
}

