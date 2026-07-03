package org.lucas.notesgenerator.services;

import jakarta.transaction.Transactional;
import org.lucas.notesgenerator.dtos.NoteReleaseUpdateDto;
import org.lucas.notesgenerator.dtos.ReleaseNoteRequestDto;
import org.lucas.notesgenerator.dtos.ReleaseNoteResponseDto;
import org.lucas.notesgenerator.mappers.NoteReleaseMapper;
import org.lucas.notesgenerator.models.ReleaseNote;
import org.lucas.notesgenerator.repositories.ReleaseNoteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ReleaseNoteServiceImpl implements ReleaseNoteService {

  final private ReleaseNoteRepository releaseNoteRepository;
  final private NoteReleaseMapper noteReleaseMapper;

  public ReleaseNoteServiceImpl(ReleaseNoteRepository releaseNoteRepository, NoteReleaseMapper noteReleaseMapper) {
    this.releaseNoteRepository = releaseNoteRepository;
    this.noteReleaseMapper = noteReleaseMapper;
  }

  @Override
  @Transactional
  public ReleaseNoteResponseDto createReleaseNote(ReleaseNoteRequestDto releaseNoteRequestDto) {
    ReleaseNote releaseNote = noteReleaseMapper.toEntity(releaseNoteRequestDto);
    ReleaseNote savedReleaseNote = releaseNoteRepository.save(releaseNote);
    return noteReleaseMapper.toReleaseNoteResponseDto(savedReleaseNote);
  }

  @Override
  public ReleaseNoteResponseDto getReleaseNoteById(UUID id) {
    ReleaseNote releaseNote = releaseNoteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Note not Found"));
    return noteReleaseMapper.toReleaseNoteResponseDto(releaseNote);
  }

  @Override
  public List<ReleaseNoteResponseDto> getAllReleaseNotes() {
    return List.of();
  }

  @Override
  @Transactional
  public ReleaseNoteResponseDto updateReleaseNote(UUID id, NoteReleaseUpdateDto noteReleaseUpdateDto) {
    ReleaseNote releaseNote = releaseNoteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Note not Found"));
    noteReleaseMapper.updateReleaseNote(noteReleaseUpdateDto, releaseNote);
    ReleaseNote savedReleaseNote = releaseNoteRepository.save(releaseNote);
    return noteReleaseMapper.toReleaseNoteResponseDto(savedReleaseNote);
  }

  @Override
  @Transactional
  public void deleteReleaseNote(UUID id) {
    releaseNoteRepository.deleteById(id);
  }
}
