package services.intf;

import entity.HumanResource;

import java.util.List;
import java.util.Optional;

public interface HumanResourceServiceIntf {
    List<HumanResource> getAll();

    Optional<HumanResource> getById(Long id);

    HumanResource save(HumanResource humanResource);

    void delete(Long id);
}
