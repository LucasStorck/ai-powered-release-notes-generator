package org.lucas.notesgenerator.mappers;

import org.lucas.notesgenerator.dtos.NoteReleaseUpdateDto;
import org.lucas.notesgenerator.dtos.ReleaseNoteRequestDto;
import org.lucas.notesgenerator.dtos.ReleaseNoteResponseDto;
import org.lucas.notesgenerator.models.ReleaseNote;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface NoteReleaseMapper {

  ReleaseNoteResponseDto toReleaseNoteResponseDto(ReleaseNote releaseNote);
  ReleaseNote toEntity(ReleaseNoteRequestDto releaseNoteRequestDto);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateReleaseNote(NoteReleaseUpdateDto  noteReleaseUpdateDto, @MappingTarget ReleaseNote releaseNote);
}
