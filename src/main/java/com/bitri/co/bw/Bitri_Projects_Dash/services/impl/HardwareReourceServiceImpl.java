package com.bitri.co.bw.Bitri_Projects_Dash.services.impl;


import com.bitri.co.bw.Bitri_Projects_Dash.entity.HardwareResource;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.HardwareResourceRepository;
import com.bitri.co.bw.Bitri_Projects_Dash.services.intf.HardwareResourceServiceIntf;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HardwareReourceServiceImpl implements HardwareResourceServiceIntf {

    private final HardwareResourceRepository hardwareResourceRepository;
    private final ResourceServiceImpl resourceServiceImpl;

    @Override
    public List<HardwareResource> getAll() {
        return hardwareResourceRepository.findAll();
    }

    @Override
    public Optional<HardwareResource> getById(Long id) {
        return hardwareResourceRepository.findById(id);
    }

    @Override
    @Transactional
    public HardwareResource save(HardwareResource hardwareResource) {
        return (HardwareResource) resourceServiceImpl.save(hardwareResource);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        hardwareResourceRepository.deleteById(id);
    }
}
