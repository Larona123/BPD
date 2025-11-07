package com.bitri.co.bw.Bitri_Projects_Dash.services.intf;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Performance;
import com.bitri.co.bw.Bitri_Projects_Dash.model.PerformanceRequest;
import com.bitri.co.bw.Bitri_Projects_Dash.model.PerformanceResponse;

import java.util.List;
import java.util.Optional;

public interface PerformanceServiceIntf {
    List<PerformanceResponse> getAll();
    Optional<PerformanceResponse> getById(Long id);
    PerformanceResponse save(PerformanceRequest performanceRequest);
    Optional<PerformanceResponse> update(Long id, PerformanceRequest performanceRequest);
    void delete(Long id);
}
