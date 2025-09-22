package org.hrsninja.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hrsninja.api.dto.*;
import org.hrsninja.api.model.CandidateStatus;
import org.hrsninja.api.service.CandidateService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CandidateDTO create(@Valid @RequestBody CreateCandidateRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public CandidateDTO update(@PathVariable UUID id, @Valid @RequestBody UpdateCandidateRequest request) {
        return service.update(id, request);
    }

    @PutMapping("/{id}/status")
    public CandidateDTO changeStatus(@PathVariable UUID id, @Valid @RequestBody ChangeStatusRequest request) {
        return service.changeStatus(id, request);
    }

    @PutMapping("/{id}/comment")
    public CandidateDTO changeComment(@PathVariable UUID id, @Valid @RequestBody ChangeCommentRequest request) {
        return service.changeComment(id, request);
    }

    @GetMapping
    public List<CandidateDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CandidateDTO findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @GetMapping("/search")
    public List<CandidateDTO> search(
            @RequestParam(required = false) String fio,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String position) {
        Set<CandidateStatus> statuses = null;
        if (status != null && !status.isBlank()) {
            statuses = Arrays.stream(status.split(","))
                .map(String::trim)
                .map(CandidateStatus::valueOf)
                .collect(Collectors.toSet());
        }

        return service.search(fio, statuses, position);
    }
} 