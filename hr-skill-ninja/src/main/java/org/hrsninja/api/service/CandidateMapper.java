package org.hrsninja.api.service;

import org.hrsninja.api.dto.CandidateDTO;
import org.hrsninja.api.model.Candidate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CandidateMapper {
    CandidateDTO toDTO(Candidate candidate);
} 