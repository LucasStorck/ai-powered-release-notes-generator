package org.lucas.notesgenerator.mappers;

import org.lucas.notesgenerator.dtos.ReleaseNoteRequestDto;
import org.lucas.notesgenerator.dtos.ReleaseNoteResponseDto;
import org.lucas.notesgenerator.models.ReleaseNote;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteReleaseMapper {

  ReleaseNoteResponseDto toReleaseNoteResponseDto(ReleaseNote releaseNote);
  ReleaseNote toEntity(ReleaseNoteRequestDto releaseNoteRequestDto);

  void updateReleaseNote()
}
