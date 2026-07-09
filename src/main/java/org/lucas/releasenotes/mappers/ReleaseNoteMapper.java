package org.lucas.releasenotes.mappers;

import org.lucas.releasenotes.dtos.ReleaseNoteUpdateDto;
import org.lucas.releasenotes.dtos.ReleaseNoteRequestDto;
import org.lucas.releasenotes.dtos.ReleaseNoteResponseDto;
import org.lucas.releasenotes.models.ReleaseNote;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ReleaseNoteMapper {

  ReleaseNoteResponseDto toReleaseNoteResponseDto(ReleaseNote releaseNote);

  ReleaseNote toEntity(ReleaseNoteRequestDto releaseNoteRequestDto);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateReleaseNote(ReleaseNoteUpdateDto noteReleaseUpdateDto, @MappingTarget ReleaseNote releaseNote);
}
