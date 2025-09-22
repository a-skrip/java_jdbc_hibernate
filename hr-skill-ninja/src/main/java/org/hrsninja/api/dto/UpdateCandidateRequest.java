package org.hrsninja.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCandidateRequest {
    @NotBlank
    @Size(max = 255)
    private String fio;

    @Min(14)
    @Max(99)
    private short age;

    @NotBlank
    @Size(max = 255)
    private String position;

    @NotBlank
    private String cvInfo;
}
