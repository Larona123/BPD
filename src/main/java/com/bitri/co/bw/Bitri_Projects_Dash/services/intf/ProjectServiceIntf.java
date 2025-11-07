package com.bitri.co.bw.Bitri_Projects_Dash.services.intf;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Project;
import com.bitri.co.bw.Bitri_Projects_Dash.model.DashboardMetricsDTO;
import com.bitri.co.bw.Bitri_Projects_Dash.model.ProjectRequest;
import com.bitri.co.bw.Bitri_Projects_Dash.model.ProjectResponse;
import com.bitri.co.bw.Bitri_Projects_Dash.model.ProjectStatusCountResponse;

import java.util.List;
import java.util.Optional;

public interface ProjectServiceIntf {
    List<ProjectResponse> getAll();
    Optional<ProjectResponse> getById(Long id);
    ProjectResponse save(ProjectRequest requestDTO);
    ProjectResponse update(Long id, ProjectRequest requestDTO);
    void delete(Long id);
    List<ProjectStatusCountResponse> getProjectStatusCounts();
    DashboardMetricsDTO getDashboardMetrics();

    DashboardMetricsDTO getProjectDashboardMetrics();
}
