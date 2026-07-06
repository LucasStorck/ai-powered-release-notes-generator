package org.lucas.releasenotes.services;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Service
public class AISummarizerServiceImpl implements AISummarizerService {

  private final RestClient restClient;
  private final double temperature;

  public AISummarizerServiceImpl(
          @Value("${ai.huggingface.base-url}") String baseUrl,
          @Value("${ai.huggingface.api-key}") String token,
          @Value("${ai.huggingface.temperature}") double temperature) {

    this.restClient = RestClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader("Authorization", "Bearer " + token)
            .defaultHeader("Content-Type", "application/json")
            .build();
    this.temperature = temperature;
  }

  @Override
  public String summarize(String cleanedPatch) {
    Map<String, Object> requestBody = Map.of(
            "inputs", "You are a professional software engineer. Summarize the following Git diff into clean, professional Markdown Release Notes. Patch data: " + cleanedPatch,
            "parameters", Map.of(
                    "max_new_tokens", 500,
                    "temperature", 0.3
            )
    );

    try {
      List<Map<String, Object>> response = restClient.post()
              .contentType(MediaType.APPLICATION_JSON)
              .body(requestBody)
              .retrieve()
              .body(List.class);

      if (response != null && !response.isEmpty()) {
        return (String) response.get(0).get("generated_text");
      }
      return "Erro: Resposta Vazia da IA.";

    } catch (Exception e) {
      return "Erro ao Processar IA: " + e.getMessage();
    }
  }
}
