package com.bitri.co.bw.Bitri_Projects_Dash.repository;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Risk;
import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.RiskAndIssueSeverity;
import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.RiskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiskRepository extends JpaRepository<Risk, Long> {

    Long countBySeverity(RiskAndIssueSeverity severity);

    Long countByStatus(RiskStatus status);

    List<Risk> findByProjectId(Long projectId);
    @Query("SELECT COUNT(r) FROM Risk r")
    Long countTotalRisks();

    @Query("SELECT COUNT(r) FROM Risk r WHERE r.severity = 'EXTREME'")
    Long countRisksExtreme();

    @Query("SELECT COUNT(r) FROM Risk r WHERE r.severity = 'HIGH'")
    Long countRisksHigh();

    @Query("SELECT COUNT(r) FROM Risk r WHERE r.severity = 'MEDIUM'")
    Long countRisksMedium();

    @Query("SELECT COUNT(r) FROM Risk r WHERE r.severity = 'LOW'")
    Long countRisksLow();

    @Query("SELECT COUNT(r) FROM Risk r WHERE r.status = 'CLOSED'")
    Long countRisksSolved();
}
