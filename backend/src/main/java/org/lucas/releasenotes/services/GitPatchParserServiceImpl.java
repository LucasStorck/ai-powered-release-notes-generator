package org.lucas.releasenotes.services;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.stream.Collectors;


@Service
public class GitPatchParserServiceImpl implements GitPatchParserService {

  @Override
  public String cleanPatch(String rawPatch) {
    if (rawPatch == null || rawPatch.trim().isEmpty()) {
      return "";
    }

    try (BufferedReader reader = new BufferedReader(new StringReader(rawPatch))) {
      return reader.lines()
              .filter(this::isRelevantLine)
              .map(this::formatLine)
              .collect(Collectors.joining("\n"));
    } catch (Exception exception) {
      return rawPatch;
    }
  }

  private String formatLine(String line) {
    if (line.startsWith("+++ b/")) {
      return "\nFile Modified: " + line.substring(6);
    } else if (line.startsWith("--- a/")) {
      return "";
    } else if (line.startsWith("+")) {
      return "Added: " + line.substring(1).trim();
    } else if (line.startsWith("-")) {
      return "Removed: " + line.substring(1).trim();
    }
    return line;
  }

  private boolean isRelevantLine(String line) {
    return line.startsWith("+++ b/") ||
            line.startsWith("--- a/") ||
            line.startsWith("+") && !line.startsWith("+++") ||
            line.startsWith("-") && !line.startsWith("---");
  }
}
