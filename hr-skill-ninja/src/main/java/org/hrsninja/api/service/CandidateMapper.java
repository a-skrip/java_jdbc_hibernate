package org.hrsninja.api.service;

import org.hrsninja.api.dto.CandidateDTO;
import org.hrsninja.api.dto.CreateCandidateRequest;
import org.hrsninja.api.model.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CandidateMapper {
    CandidateDTO toDTO(Candidate candidate);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "comment", ignore = true)
    Candidate toEntity(CreateCandidateRequest createCandidateRequest);
} 