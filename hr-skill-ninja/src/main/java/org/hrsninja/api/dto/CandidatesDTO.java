package org.hrsninja.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CandidatesDTO {
    private List<CandidateDTO> candidates;
}
