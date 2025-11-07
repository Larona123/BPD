package com.bitri.co.bw.Bitri_Projects_Dash.services.intf;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.HumanResource;
import com.bitri.co.bw.Bitri_Projects_Dash.model.HumanResourceRequest;
import com.bitri.co.bw.Bitri_Projects_Dash.model.HumanResourceResponse;

import java.util.List;
import java.util.Optional;

public interface HumanResourceServiceIntf {

    HumanResourceResponse create(HumanResourceRequest request);

    List<HumanResourceResponse> findAll();

    HumanResourceResponse findById(Long id);

    HumanResourceResponse update(Long id, HumanResourceRequest request);

    void delete(Long id);

    List<HumanResourceResponse> findAllByProjectId(Long projectId);

}
