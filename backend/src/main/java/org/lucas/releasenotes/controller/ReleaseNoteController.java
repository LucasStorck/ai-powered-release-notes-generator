package org.lucas.releasenotes.controller;

import jakarta.validation.Valid;
import org.lucas.releasenotes.dtos.ReleaseNoteUpdateDto;
import org.lucas.releasenotes.dtos.ReleaseNoteRequestDto;
import org.lucas.releasenotes.dtos.ReleaseNoteResponseDto;
import org.lucas.releasenotes.services.ReleaseNoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/release-notes")
public class ReleaseNoteController {

  final private ReleaseNoteService releaseNoteService;

  public ReleaseNoteController(ReleaseNoteService releaseNoteService) {
    this.releaseNoteService = releaseNoteService;
  }

  @PostMapping
  public ResponseEntity<ReleaseNoteResponseDto> createReleaseNote(
      @Valid @RequestBody ReleaseNoteRequestDto releaseNoteRequestDto) {
    return ResponseEntity.ok(releaseNoteService.createReleaseNote(releaseNoteRequestDto));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ReleaseNoteResponseDto> getReleaseNoteById(@PathVariable UUID id) {
    return ResponseEntity.ok(releaseNoteService.getReleaseNoteById(id));
  }

  @GetMapping
  public ResponseEntity<List<ReleaseNoteResponseDto>> getAllReleaseNotes() {
    return ResponseEntity.ok(releaseNoteService.getAllReleaseNotes());
  }

  @PutMapping("/{id}")
  public ResponseEntity<ReleaseNoteResponseDto> updateReleaseNote(
      @PathVariable UUID id,
      @Valid @RequestBody ReleaseNoteUpdateDto noteReleaseUpdateDto) {
    return ResponseEntity.ok(releaseNoteService.updateReleaseNote(id, noteReleaseUpdateDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteReleaseNote(@PathVariable UUID id) {
    releaseNoteService.deleteReleaseNote(id);
    return ResponseEntity.noContent().build();
  }
}
