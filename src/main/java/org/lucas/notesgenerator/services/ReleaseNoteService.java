package org.lucas.notesgenerator.services;

import org.lucas.notesgenerator.dtos.NoteReleaseUpdateDto;
import org.lucas.notesgenerator.dtos.ReleaseNoteRequestDto;
import org.lucas.notesgenerator.dtos.ReleaseNoteResponseDto;

import java.util.List;
import java.util.UUID;

public interface ReleaseNoteService {

  ReleaseNoteResponseDto createReleaseNote(ReleaseNoteRequestDto releaseNoteRequestDto);

  ReleaseNoteResponseDto getReleaseNoteById(UUID id);

  List<ReleaseNoteResponseDto> getAllReleaseNotes();

  ReleaseNoteResponseDto updateReleaseNote(UUID id, NoteReleaseUpdateDto noteReleaseUpdateDto);

  void deleteReleaseNote(UUID id);

}
