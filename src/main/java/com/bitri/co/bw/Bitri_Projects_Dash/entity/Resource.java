package com.bitri.co.bw.Bitri_Projects_Dash.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "resource")
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,         // use the type name in JSON
        include = JsonTypeInfo.As.PROPERTY, // include it as a field in JSON
        property = "type"                   // the field name
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = HumanResource.class, name = "human"),
        @JsonSubTypes.Type(value = HardwareResource.class, name = "hardware")
})

public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String name;

    @Column(name = "cost")
    protected Double cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    protected Project project;
}
