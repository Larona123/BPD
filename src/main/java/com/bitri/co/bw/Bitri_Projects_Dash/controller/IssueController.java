package com.bitri.co.bw.Bitri_Projects_Dash.controller;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Issue;
import com.bitri.co.bw.Bitri_Projects_Dash.model.IssueRequest;
import com.bitri.co.bw.Bitri_Projects_Dash.model.IssueResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bitri.co.bw.Bitri_Projects_Dash.services.intf.IssueServiceIntf;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
@RequiredArgsConstructor
@CrossOrigin("*")
public class IssueController {

    private final IssueServiceIntf issueService;
    @GetMapping
    public ResponseEntity<List<IssueResponse>> getAllIssues() {
        return ResponseEntity.ok(issueService.getAllIssues());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IssueResponse> getIssueById(@PathVariable Long id) {
        return ResponseEntity.ok(issueService.getIssueById(id));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<IssueResponse>> getIssuesByProjectId(@PathVariable Long projectId) {
        return ResponseEntity.ok(issueService.getIssuesByProjectId(projectId));
    }

    @PostMapping
    public ResponseEntity<IssueResponse> create(@RequestBody IssueRequest request) {
        IssueResponse createdIssue = issueService.create(request);
        return new ResponseEntity<>(createdIssue, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IssueResponse> update(@PathVariable Long id, @RequestBody IssueRequest request) {
        IssueResponse updatedIssue = issueService.update(id, request);
        return ResponseEntity.ok(updatedIssue);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIssue(@PathVariable Long id) {
        issueService.deleteIssue(id);
        return ResponseEntity.noContent().build();
    }
}
