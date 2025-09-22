package org.hrsninja.api.dto;

import lombok.Getter;
import lombok.Setter;
import org.hrsninja.api.model.CandidateStatus;

import java.util.UUID;

@Getter
@Setter
public class CandidateDTO {
    private UUID id;
    private String fio;
    private short age;
    private String position;
    private String cvInfo;
    private String comment;
    private CandidateStatus status;
} 