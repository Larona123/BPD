package com.bitri.co.bw.Bitri_Projects_Dash.services.impl;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Risk;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.RiskRepository;
import com.bitri.co.bw.Bitri_Projects_Dash.services.intf.RiskServiceIntf;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RiskServiceImpl implements RiskServiceIntf {

    private final RiskRepository riskRepository;

    @Override
    public List<Risk> getAll() {
        return riskRepository.findAll();
    }

    @Override
    public Optional<Risk> getById(Long id) {
        return riskRepository.findById(id);
    }

    @Override
    public Risk save(Risk risk) {
        return riskRepository.save(risk);
    }

    @Override
    public void delete(Long id) {
        riskRepository.deleteById(id);
    }

    @Override
    public Risk updateRisk(Long id, Risk risk) {
        return null;
    }

    @Override
    public List<Risk> getRisksByProjectId(Long projectId) {
        return null;
    }
}
