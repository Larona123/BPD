package com.bitri.co.bw.Bitri_Projects_Dash.services.intf;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Issue;
import com.bitri.co.bw.Bitri_Projects_Dash.model.IssueRequest;
import com.bitri.co.bw.Bitri_Projects_Dash.model.IssueResponse;

import java.util.List;
import java.util.Optional;

public interface IssueServiceIntf {
    IssueResponse create(IssueRequest request);
    IssueResponse getIssueById(Long id);
    List<IssueResponse> getAllIssues();
    List<IssueResponse> getIssuesByProjectId(Long projectId);
    IssueResponse update(Long id, IssueRequest request);
    void deleteIssue(Long id);
}
