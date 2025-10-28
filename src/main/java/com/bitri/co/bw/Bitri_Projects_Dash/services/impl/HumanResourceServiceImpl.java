package com.bitri.co.bw.Bitri_Projects_Dash.services.impl;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.HumanResource;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.HumanResourceRepository;
import com.bitri.co.bw.Bitri_Projects_Dash.services.intf.HumanResourceServiceIntf;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HumanResourceServiceImpl implements HumanResourceServiceIntf {

    private final HumanResourceRepository humanResourceRepository;
    private final ResourceServiceImpl resourceServiceImpl;

    @Override
    public List<HumanResource> getAll() {
        return humanResourceRepository.findAll();
    }

    @Override
    public Optional<HumanResource> getById(Long id) {
        return humanResourceRepository.findById(id);
    }

    @Override
    @Transactional
    public HumanResource save(HumanResource humanResource) {
        return (HumanResource) resourceServiceImpl.save(humanResource);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        humanResourceRepository.deleteById(id);
    }
}
