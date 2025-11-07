package com.bitri.co.bw.Bitri_Projects_Dash.services.impl;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Project;
import com.bitri.co.bw.Bitri_Projects_Dash.entity.Risk;
import com.bitri.co.bw.Bitri_Projects_Dash.exception.EntityNotFoundException;
import com.bitri.co.bw.Bitri_Projects_Dash.model.RiskRequest;
import com.bitri.co.bw.Bitri_Projects_Dash.model.RiskResponse;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.RiskRepository;
import com.bitri.co.bw.Bitri_Projects_Dash.services.intf.RiskServiceIntf;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RiskServiceImpl implements RiskServiceIntf {

    private final RiskRepository riskRepository;
    private final ProjectRepository projectRepository;
    private RiskResponse mapToResponseDTO(Risk risk) {
        if (risk == null) return null;
        return RiskResponse.builder()
                .id(risk.getId())
                .description(risk.getDescription())
                .severity(risk.getSeverity())
                .status(risk.getStatus())
                .owner(risk.getOwner())
                .dateRaised(risk.getDateRaised())
                .mitigationPlan(risk.getMitigationPlan())
                .project(risk.getProject())
                .build();
    }

    private Risk mapRequestToEntity(RiskRequest request) {
        Risk risk = new Risk();
        risk.setDescription(request.getDescription());
        risk.setSeverity(request.getSeverity());
        risk.setStatus(request.getStatus());
        risk.setOwner(request.getOwner());
        risk.setDateRaised(request.getDateRaised());
        risk.setMitigationPlan(request.getMitigationPlan());
        return risk;
    }

    @Override
    public List<RiskResponse> getAll() {
        return riskRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RiskResponse> getById(Long id) {
        return riskRepository.findById(id)
                .map(this::mapToResponseDTO);
    }

    @Override
    public Optional<RiskResponse> getRiskById(Long riskId) {
        return riskRepository.findById(riskId)
                .map(this::mapToResponseDTO);
    }

    public RiskResponse save(RiskRequest riskRequestDTO) {
        Project project = projectRepository.findById(riskRequestDTO.getProjectId())
                .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + riskRequestDTO.getProjectId()));

        Risk newRisk = Risk.builder()
                .description(riskRequestDTO.getDescription())
                .severity(riskRequestDTO.getSeverity())
                .status(riskRequestDTO.getStatus())
                .owner(riskRequestDTO.getOwner())
                .dateRaised(riskRequestDTO.getDateRaised() != null ? riskRequestDTO.getDateRaised() : LocalDate.now())
                .mitigationPlan(riskRequestDTO.getMitigationPlan())
                .project(project)
                .build();

        Risk savedRisk = riskRepository.save(newRisk);

        return mapToResponseDTO(savedRisk);
    }

    @Override
    public RiskResponse updateRisk(Long id, RiskRequest riskRequestDTO) {
        Risk existingRisk = riskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Risk not found with ID: " + id));

        Project project = existingRisk.getProject();
        if (riskRequestDTO.getProjectId() != null && !riskRequestDTO.getProjectId().equals(project.getId())) {
            project = projectRepository.findById(riskRequestDTO.getProjectId())
                    .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + riskRequestDTO.getProjectId()));
        }

        Optional.ofNullable(riskRequestDTO.getDescription()).ifPresent(existingRisk::setDescription);
        Optional.ofNullable(riskRequestDTO.getSeverity()).ifPresent(existingRisk::setSeverity);
        Optional.ofNullable(riskRequestDTO.getStatus()).ifPresent(existingRisk::setStatus);
        Optional.ofNullable(riskRequestDTO.getOwner()).ifPresent(existingRisk::setOwner);
        Optional.ofNullable(riskRequestDTO.getDateRaised()).ifPresent(existingRisk::setDateRaised);
        Optional.ofNullable(riskRequestDTO.getMitigationPlan()).ifPresent(existingRisk::setMitigationPlan);

        existingRisk.setProject(project);

        Risk updatedRisk = riskRepository.save(existingRisk);
        return mapToResponseDTO(updatedRisk);
    }

    @Override
    public void delete(Long id) {
        riskRepository.deleteById(id);
    }

    @Override
    public List<RiskResponse> getRisksByProjectId(Long projectId) {
        return riskRepository.findByProjectId(projectId).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }
}
