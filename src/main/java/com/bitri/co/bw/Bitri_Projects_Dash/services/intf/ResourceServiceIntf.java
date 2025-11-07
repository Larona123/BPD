package com.bitri.co.bw.Bitri_Projects_Dash.services.intf;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Resource;
import com.bitri.co.bw.Bitri_Projects_Dash.model.HardwareResourceResponse;

import java.util.List;
import java.util.Optional;

public interface ResourceServiceIntf {
    List<HardwareResourceResponse> getAll();
    Optional<HardwareResourceResponse> getById(Long id);
    Resource save(Resource resource);
    void delete(Long id);
}
