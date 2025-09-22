package org.hrsninja.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hrsninja.api.model.CandidateStatus;

@Getter
@Setter
public class ChangeStatusRequest {
    @NotNull
    private CandidateStatus status;
}
