package org.lucas.releasenotes.services;

import org.lucas.releasenotes.dtos.ReleaseNoteUpdateDto;
import org.lucas.releasenotes.dtos.ReleaseNoteRequestDto;
import org.lucas.releasenotes.dtos.ReleaseNoteResponseDto;

import java.util.List;
import java.util.UUID;

public interface ReleaseNoteService {

  ReleaseNoteResponseDto createReleaseNote(ReleaseNoteRequestDto releaseNoteRequestDto);

  ReleaseNoteResponseDto getReleaseNoteById(UUID id);

  List<ReleaseNoteResponseDto> getAllReleaseNotes();

  ReleaseNoteResponseDto updateReleaseNote(UUID id, ReleaseNoteUpdateDto noteReleaseUpdateDto);

  void deleteReleaseNote(UUID id);

}
