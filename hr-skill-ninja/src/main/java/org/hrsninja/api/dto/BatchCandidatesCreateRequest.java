package org.hrsninja.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BatchCandidatesCreateRequest {
    private List<CreateCandidateRequest> candidates;
}


