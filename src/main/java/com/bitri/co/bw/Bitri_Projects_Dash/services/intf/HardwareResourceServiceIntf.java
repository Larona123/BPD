package com.bitri.co.bw.Bitri_Projects_Dash.services.intf;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.HardwareResource;
import com.bitri.co.bw.Bitri_Projects_Dash.model.HardwareResourceRequest;
import com.bitri.co.bw.Bitri_Projects_Dash.model.HardwareResourceResponse;

import java.util.List;
import java.util.Optional;

public interface HardwareResourceServiceIntf {
    HardwareResourceResponse create(HardwareResourceRequest request);

    List<HardwareResourceResponse> findAll();

    HardwareResourceResponse findById(Long id);

    HardwareResourceResponse update(Long id, HardwareResourceRequest request);

    void delete(Long id);

    List<HardwareResourceResponse> findAllByProjectId(Long projectId);
}
