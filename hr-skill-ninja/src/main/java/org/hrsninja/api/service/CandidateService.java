package org.hrsninja.api.service;

import org.hrsninja.api.dto.*;
import org.hrsninja.api.model.CandidateStatus;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface CandidateService {
    CandidateDTO create(CreateCandidateRequest request);
    CandidateDTO update(UUID id, UpdateCandidateRequest request);
    CandidateDTO changeStatus(UUID id, ChangeStatusRequest request);
    CandidateDTO changeComment(UUID id, ChangeCommentRequest request);
    List<CandidateDTO> findAll();
    CandidateDTO findById(UUID id);
    List<CandidateDTO> search(String fio, Set<CandidateStatus> statuses, String position);
}
