package com.bitri.co.bw.Bitri_Projects_Dash.repository;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {
}
