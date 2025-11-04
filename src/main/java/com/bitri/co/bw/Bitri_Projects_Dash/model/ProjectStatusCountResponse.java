package com.bitri.co.bw.Bitri_Projects_Dash.model;

import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.ProjectStatus;
import lombok.Value;

@Value
public class ProjectStatusCountResponse {

    private ProjectStatus status;
    private Long count;

    public ProjectStatusCountResponse(ProjectStatus status, Long count) {
        this.status = status;
        this.count = count;
    }
    @Override
    public String toString() {
        return "ProjectStatusCountResponse{" +
                "status='" + status + '\'' +
                ", count=" + count +
                '}';
    }

}
