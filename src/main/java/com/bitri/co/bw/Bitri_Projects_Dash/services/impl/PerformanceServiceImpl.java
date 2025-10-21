package com.bitri.co.bw.Bitri_Projects_Dash.services.impl;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Performance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.PerformanceRepository;
import com.bitri.co.bw.Bitri_Projects_Dash.services.intf.PerformanceServiceIntf;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PerformanceServiceImpl implements PerformanceServiceIntf {

    private final PerformanceRepository performanceRepository;

    @Override
    public List<Performance> getAll() {
        return performanceRepository.findAll();
    }

    @Override
    public Optional<Performance> getById(Long id) {
        return performanceRepository.findById(id);
    }

    @Override
    public Performance save(Performance performance) {
        return performanceRepository.save(performance);
    }

    @Override
    public void delete(Long id) {
        performanceRepository.deleteById(id);
    }
}
