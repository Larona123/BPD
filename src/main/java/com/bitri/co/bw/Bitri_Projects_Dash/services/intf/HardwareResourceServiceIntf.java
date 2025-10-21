package com.bitri.co.bw.Bitri_Projects_Dash.services.intf;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.HardwareResource;

import java.util.List;
import java.util.Optional;

public interface HardwareResourceServiceIntf {
    List<HardwareResource> getAll();

    Optional<HardwareResource> getById(Long id);

    HardwareResource save(HardwareResource hardwareResource);

    void delete(Long id);
}
