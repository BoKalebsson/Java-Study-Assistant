package io.github.bokalebsson.javastudyassistant.dto;

import io.github.bokalebsson.javastudyassistant.prompt.ExpertiseLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatRequest {

    @NotBlank
    private String message;

    @NotNull
    private ExpertiseLevel level;
}
