package com.bitri.co.bw.Bitri_Projects_Dash.services.intf;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Risk;

import java.util.List;
import java.util.Optional;

public interface RiskServiceIntf {
    List<Risk> getAll();

    Optional<Risk> getById(Long id);

    Risk save(Risk risk);

    void delete(Long id);

    Risk updateRisk(Long id, Risk risk);

    List<Risk> getRisksByProjectId(Long projectId);
}
