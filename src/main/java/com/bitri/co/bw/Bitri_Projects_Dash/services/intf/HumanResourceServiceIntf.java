package com.bitri.co.bw.Bitri_Projects_Dash.services.intf;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.HumanResource;

import java.util.List;
import java.util.Optional;

public interface HumanResourceServiceIntf {
    List<HumanResource> getAll();

    Optional<HumanResource> getById(Long id);

    HumanResource save(HumanResource humanResource);

    void delete(Long id);
}
