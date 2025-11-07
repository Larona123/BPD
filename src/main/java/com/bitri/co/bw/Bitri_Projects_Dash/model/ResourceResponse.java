package com.bitri.co.bw.Bitri_Projects_Dash.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class ResourceResponse {
    protected Long id;
    protected String name;
    protected Double cost;
    protected String type;

    protected Long projectId;
    protected ProjectResponse project;
}
