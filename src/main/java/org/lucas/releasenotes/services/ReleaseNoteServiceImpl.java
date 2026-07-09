package org.lucas.releasenotes.services;

import jakarta.transaction.Transactional;
import org.lucas.releasenotes.dtos.ReleaseNoteUpdateDto;
import org.lucas.releasenotes.dtos.ReleaseNoteRequestDto;
import org.lucas.releasenotes.dtos.ReleaseNoteResponseDto;
import org.lucas.releasenotes.exceptions.ResourceNotFoundException;
import org.lucas.releasenotes.mappers.ReleaseNoteMapper;
import org.lucas.releasenotes.models.ReleaseNote;
import org.lucas.releasenotes.repositories.ReleaseNoteRepository;
import org.lucas.releasenotes.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReleaseNoteServiceImpl implements ReleaseNoteService {

  final private ReleaseNoteRepository releaseNoteRepository;
  final private ReleaseNoteMapper releaseNoteMapper;
  final private GitPatchParserService gitPatchParserService;
  final private AISummarizerService aISummarizerService;
  final private UserRepository userRepository;

  public ReleaseNoteServiceImpl(ReleaseNoteRepository releaseNoteRepository, ReleaseNoteMapper releaseNoteMapper,
                                GitPatchParserService gitPatchParserService, AISummarizerService aISummarizerService,
                                UserRepository userRepository) {
    this.releaseNoteRepository = releaseNoteRepository;
    this.releaseNoteMapper = releaseNoteMapper;
    this.gitPatchParserService = gitPatchParserService;
    this.aISummarizerService = aISummarizerService;
    this.userRepository = userRepository;
  }

  @Override
  @Transactional
  public ReleaseNoteResponseDto createReleaseNote(ReleaseNoteRequestDto releaseNoteRequestDto) {

    String diffClean = gitPatchParserService.cleanPatch(releaseNoteRequestDto.rawPatch());
    String summary = aISummarizerService.summarize(diffClean);
    var auth = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();

    String username = (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal()))
            ? auth.getName()
            : "admin";

    org.lucas.releasenotes.models.User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("User not Found: " + username));

    ReleaseNote releaseNote = releaseNoteMapper.toEntity(releaseNoteRequestDto);

    releaseNote.setCleanPatch(diffClean);
    releaseNote.setMarkdownSummary(summary);
    releaseNote.setModelUsed("Llama-3.1-8B-Instruct");
    releaseNote.setUser(user);

    ReleaseNote savedReleaseNote = releaseNoteRepository.save(releaseNote);
    return releaseNoteMapper.toReleaseNoteResponseDto(savedReleaseNote);
  }

  @Override
  public ReleaseNoteResponseDto getReleaseNoteById(UUID id) {
    ReleaseNote releaseNote = releaseNoteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Note not Found"));
    return releaseNoteMapper.toReleaseNoteResponseDto(releaseNote);
  }

  @Override
  public List<ReleaseNoteResponseDto> getAllReleaseNotes() {
    return releaseNoteRepository.findAll()
            .stream()
            .map(releaseNoteMapper::toReleaseNoteResponseDto)
            .toList();
  }

  @Override
  @Transactional
  public ReleaseNoteResponseDto updateReleaseNote(UUID id, ReleaseNoteUpdateDto noteReleaseUpdateDto) {
    ReleaseNote releaseNote = releaseNoteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Note not Found"));
    releaseNoteMapper.updateReleaseNote(noteReleaseUpdateDto, releaseNote);
    ReleaseNote savedReleaseNote = releaseNoteRepository.save(releaseNote);
    return releaseNoteMapper.toReleaseNoteResponseDto(savedReleaseNote);
  }

  @Override
  @Transactional
  public void deleteReleaseNote(UUID id) {
    releaseNoteRepository.deleteById(id);
  }
}
