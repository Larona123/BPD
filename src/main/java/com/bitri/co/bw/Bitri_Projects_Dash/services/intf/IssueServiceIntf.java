package com.bitri.co.bw.Bitri_Projects_Dash.services.intf;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Issue;

import java.util.List;
import java.util.Optional;

public interface IssueServiceIntf {
    List<Issue> getAll();

    Optional<Issue> getById(Long id);

    Issue save(Issue issue);

    void delete(Long id);
}
