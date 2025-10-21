package com.bitri.co.bw.Bitri_Projects_Dash.services.intf;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Performance;

import java.util.List;
import java.util.Optional;

public interface PerformanceServiceIntf {
    List<Performance> getAll();

    Optional<Performance> getById(Long id);

    Performance save(Performance performance);

    void delete(Long id);
}
