package org.lucas.releasenotes.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Service
public class AISummarizerServiceImpl implements AISummarizerService {

  private final RestClient restClient;
  private final double temperature;
  private final String model;

  public AISummarizerServiceImpl(
          @Value("${ai.huggingface.base-url}") String baseUrl,
          @Value("${ai.huggingface.api-key}") String token,
          @Value("${ai.huggingface.temperature}") double temperature,
          @Value("${ai.huggingface.model}") String model) {

    this.restClient = RestClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader("Authorization", "Bearer " + token)
            .defaultHeader("Content-Type", "application/json")
            .build();
    this.temperature = temperature;
    this.model = model;
  }

  @Override
  public String summarize(String cleanedPatch) {
    String modelPath = "/models/" + this.model;

    Map<String, Object> requestBody = Map.of(
            "inputs", "You are a professional software engineer. Summarize the following Git diff into clean, professional Markdown Release Notes. Patch data: " + cleanedPatch,
            "parameters", Map.of(
                    "max_new_tokens", 500,
                    "temperature", this.temperature
            )
    );

    try {
      var response = restClient.post()
              .uri(modelPath)
              .body(requestBody)
              .retrieve()
              .body(List.class);

      if (response != null && !response.isEmpty()) {
        Map<String, Object> firstEntry = (Map<String, Object>) response.get(0);
        return (String) firstEntry.getOrDefault("generated_text", "Erro: Campo 'generated_text' ausente.");
      }
      return "Erro: Resposta Vazia da IA.";

    } catch (Exception e) {
      return "Erro ao Processar IA: " + e.getMessage();
    }
  }
}
