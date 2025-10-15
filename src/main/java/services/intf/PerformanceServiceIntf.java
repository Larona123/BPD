package services.intf;

import entity.Performance;

import java.util.List;
import java.util.Optional;

public interface PerformanceServiceIntf {
    List<Performance> getAll();

    Optional<Performance> getById(Long id);

    Performance save(Performance performance);

    void delete(Long id);
}
