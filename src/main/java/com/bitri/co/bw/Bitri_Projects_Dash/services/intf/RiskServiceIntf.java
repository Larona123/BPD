package com.bitri.co.bw.Bitri_Projects_Dash.services.intf;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Risk;
import com.bitri.co.bw.Bitri_Projects_Dash.model.RiskRequest;
import com.bitri.co.bw.Bitri_Projects_Dash.model.RiskResponse;

import java.util.List;
import java.util.Optional;

public interface RiskServiceIntf {

    List<RiskResponse> getAll();
    Optional<RiskResponse> getById(Long id);
    RiskResponse save(RiskRequest riskRequestDTO);
    void delete(Long id);
    RiskResponse updateRisk(Long id, RiskRequest riskRequestDTO);
    List<RiskResponse> getRisksByProjectId(Long projectId);

    Optional<RiskResponse> getRiskById(Long riskId);
}
