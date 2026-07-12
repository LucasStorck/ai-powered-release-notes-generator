package org.lucas.releasenotes.dtos;

import jakarta.validation.constraints.NotBlank;

public record ReleaseNoteRequestDto(
        @NotBlank(message = "The Patch (rawPatch) Cannot be Empty.")
        String rawPatch,

        @NotBlank(message = "The Version Tag is Mandatory.")
        String versionTag,

        @NotBlank(message = "The Repository Name is Required.")
        String repositoryName
) {
}
