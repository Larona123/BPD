package services.intf;

import entity.Risk;

import java.util.List;
import java.util.Optional;

public interface RiskServiceIntf {
    List<Risk> getAll();

    Optional<Risk> getById(Long id);

    Risk save(Risk risk);

    void delete(Long id);
}
