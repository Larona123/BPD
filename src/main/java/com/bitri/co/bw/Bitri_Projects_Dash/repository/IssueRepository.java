package com.bitri.co.bw.Bitri_Projects_Dash.repository;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    Long countByStatus(String status);

    // Find issues by priority
    List<Issue> findByPriority(String priority);

    // Find issues by project ID
    List<Issue> findByProjectId(Long projectId);
    @Query("SELECT COUNT(i) FROM Issue i")
    Long countTotalIssues();

    @Query("SELECT COUNT(i) FROM Issue i WHERE i.status = 'OPEN'")
    Long countIssuesOpen();

    @Query("SELECT COUNT(i) FROM Issue i WHERE i.status = 'CLOSED'")
    Long countIssuesClosed();

    // Assuming 'Solved Issues' is distinct from 'Closed' or maps to a specific status/flag
    @Query("SELECT COUNT(i) FROM Issue i WHERE i.status = CLOSED")
    Long countIssuesSolved();
}
