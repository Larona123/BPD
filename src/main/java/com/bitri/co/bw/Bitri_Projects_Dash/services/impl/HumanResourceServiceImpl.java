package com.bitri.co.bw.Bitri_Projects_Dash.services.impl;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.HumanResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.HumanResourceRepository;
import com.bitri.co.bw.Bitri_Projects_Dash.services.intf.HumanResourceServiceIntf;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HumanResourceServiceImpl implements HumanResourceServiceIntf {

    final HumanResourceRepository humanResourceRepository;

    @Override
    public List<HumanResource> getAll() {
        return humanResourceRepository.findAll();
    }

    @Override
    public Optional<HumanResource> getById(Long id) {
        return humanResourceRepository.findById(id);
    }

    @Override
    public HumanResource save(HumanResource humanResource) {
        return humanResourceRepository.save(humanResource);
    }

    @Override
    public void delete(Long id) {
        humanResourceRepository.deleteById(id);
    }
}
