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
    List<Map<String, String>> messages = List.of(
            Map.of(
                    "role", "system",
                    "content", "You are a professional software engineer specialized in writing Release Notes. " +
                               "Given a cleaned Git diff, produce clear, concise and professional Release Notes in Markdown format. " +
                               "Use sections like '## What's Changed', '### New Features', '### Bug Fixes', '### Refactoring'. " +
                               "Be objective and focus on the user impact of each change."
            ),
            Map.of(
                    "role", "user",
                    "content", "Generate Release Notes from the following Git diff:\n\n" + cleanedPatch
            )
    );

    Map<String, Object> requestBody = Map.of(
            "model", this.model,
            "messages", messages,
            "max_tokens", 500,
            "temperature", this.temperature
    );

    try {
      @SuppressWarnings("unchecked")
      Map<String, Object> response = restClient.post()
              .uri("/v1/chat/completions")
              .body(requestBody)
              .retrieve()
              .body(Map.class);

      if (response != null) {
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
        if (choices != null && !choices.isEmpty()) {
          @SuppressWarnings("unchecked")
          Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
          if (message != null) {
            return (String) message.getOrDefault("content", "Erro: Campo 'content' ausente na resposta.");
          }
        }
      }
      return "Erro: Resposta vazia ou formato inesperado da IA.";

    } catch (Exception e) {
      return "Erro ao processar IA: " + e.getMessage();
    }
  }
}
