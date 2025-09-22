package org.hrsninja.api.repository;

import org.hrsninja.api.model.Candidate;
import org.hrsninja.api.model.CandidateStatus;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface CandidateRepository {
    Candidate save(Candidate candidate);
    Candidate update(Candidate candidate);
    Optional<Candidate> findById(UUID id);
    List<Candidate> findAll();
    List<Candidate> search(String fio, Set<CandidateStatus> statuses, String position);
} 