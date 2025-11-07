package com.bitri.co.bw.Bitri_Projects_Dash.services.impl;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Client;
import com.bitri.co.bw.Bitri_Projects_Dash.entity.Issue;
import com.bitri.co.bw.Bitri_Projects_Dash.entity.Project;
import com.bitri.co.bw.Bitri_Projects_Dash.entity.User;
import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.IssueStatus;
import com.bitri.co.bw.Bitri_Projects_Dash.exception.EntityNotFoundException;
import com.bitri.co.bw.Bitri_Projects_Dash.model.ClientResponse;
import com.bitri.co.bw.Bitri_Projects_Dash.model.IssueRequest;
import com.bitri.co.bw.Bitri_Projects_Dash.model.IssueResponse;
import com.bitri.co.bw.Bitri_Projects_Dash.model.ProjectResponse;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.IssueRepository;
import com.bitri.co.bw.Bitri_Projects_Dash.services.intf.IssueServiceIntf;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class IssueServiceImpl implements IssueServiceIntf {

    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;

    private static final String ISSUE_NOT_FOUND_MSG = "Issue not found with ID: ";
    private static final String PROJECT_NOT_FOUND_MSG = "Project not found with ID: ";

    @Override
    public IssueResponse create(IssueRequest request) {
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new EntityNotFoundException(PROJECT_NOT_FOUND_MSG + request.getProjectId()));

        Issue issue = toEntity(request, project);
        issue.setStatus(IssueStatus.OPEN);

        Issue savedIssue = issueRepository.save(issue);

        return toResponse(savedIssue);
    }

    @Override
    @Transactional
    public IssueResponse getIssueById(Long id) {
        Issue issue = issueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ISSUE_NOT_FOUND_MSG + id));
        return toResponse(issue);
    }

    @Override
    @Transactional
    public List<IssueResponse> getAllIssues() {
        return issueRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<IssueResponse> getIssuesByProjectId(Long projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new EntityNotFoundException(PROJECT_NOT_FOUND_MSG + projectId);
        }
        return issueRepository.findByProjectId(projectId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public IssueResponse update(Long id, IssueRequest request) {
        Issue existingIssue = issueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ISSUE_NOT_FOUND_MSG + id));

        existingIssue.setTitle(request.getTitle());
        existingIssue.setDescription(request.getDescription());
        existingIssue.setSeverity(request.getSeverity());
        existingIssue.setPriority(request.getPriority());
        existingIssue.setReportedOn(request.getReportedOn());
        existingIssue.setReportedBy(request.getReportedBy());

        Issue updatedIssue = issueRepository.save(existingIssue);
        return toResponse(updatedIssue);
    }

    @Override
    public void deleteIssue(Long id) {
        if (!issueRepository.existsById(id)) {
            throw new EntityNotFoundException(ISSUE_NOT_FOUND_MSG + id);
        }
        issueRepository.deleteById(id);
    }

    private Issue toEntity(IssueRequest request, Project project) {
        return Issue.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .severity(request.getSeverity())
                .priority(request.getPriority())
                .reportedOn(request.getReportedOn())
                .reportedBy(request.getReportedBy())
                .project(project)
                .build();
    }

    private IssueResponse toResponse(Issue issue) {
        return IssueResponse.builder()
                .id(issue.getId())
                .title(issue.getTitle())
                .description(issue.getDescription())
                .status(issue.getStatus())
                .severity(issue.getSeverity())
                .priority(issue.getPriority())
                .reportedOn(issue.getReportedOn())
                .reportedBy(issue.getReportedBy())
                .project(toProjectResponse(issue.getProject()))
                .build();
    }

    private ProjectResponse toProjectResponse(Project project) {
        if (project == null) {
            return null;
        }
        Client projectOwner = project.getOwner();

        ClientResponse clientResponse = null;
        if (projectOwner != null) {
            clientResponse = ClientResponse.builder()
                    .id(projectOwner.getId())
                    .name(projectOwner.getName())
                    .email(projectOwner.getEmail())
                    .company(projectOwner.getCompany())
                    .phoneNumber(projectOwner.getPhoneNumber())
                    .build();
        }

        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .budget(project.getBudget())
                .description(project.getDescription())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .status(project.getStatus())
                .projectManager(project.getProjectManager())
                .owner(clientResponse)
                .build();
    }
}
