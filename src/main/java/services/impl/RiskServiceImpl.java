package services.impl;

import entity.Risk;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.RiskRepository;
import services.intf.RiskServiceIntf;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RiskServiceImpl implements RiskServiceIntf {

    private final RiskRepository riskRepository;

    @Override
    public List<Risk> getAll() {
        return riskRepository.findAll();
    }

    @Override
    public Optional<Risk> getById(Long id) {
        return riskRepository.findById(id);
    }

    @Override
    public Risk save(Risk risk) {
        return riskRepository.save(risk);
    }

    @Override
    public void delete(Long id) {
        riskRepository.deleteById(id);
    }
}
