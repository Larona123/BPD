package services.impl;

import entity.HumanResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.HumanResourceRepository;
import services.intf.HumanResourceServiceIntf;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HumanResourceServiceImpl implements HumanResourceServiceIntf {

    final HumanResourceRepository humanResourceRepository;

    @Override
    public List<HumanResource> getAll() {
        return humanResourceRepository.findAll();
    }

    @Override
    public Optional<HumanResource> getById(Long id) {
        return humanResourceRepository.findById(id);
    }

    @Override
    public HumanResource save(HumanResource humanResource) {
        return humanResourceRepository.save(humanResource);
    }

    @Override
    public void delete(Long id) {
        humanResourceRepository.deleteById(id);
    }
}
