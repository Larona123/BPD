package services.impl;


import entity.HardwareResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.HardwareResourceRepository;
import services.intf.HardwareResourceServiceIntf;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HardwareReourceServiceImpl implements HardwareResourceServiceIntf {

    private final HardwareResourceRepository hardwareResourceRepository;

    @Override
    public List<HardwareResource> getAll() {
        return hardwareResourceRepository.findAll();
    }

    @Override
    public Optional<HardwareResource> getById(Long id) {
        return hardwareResourceRepository.findById(id);
    }

    @Override
    public HardwareResource save(HardwareResource hardwareResource) {
        return hardwareResourceRepository.save(hardwareResource);
    }

    @Override
    public void delete(Long id) {
        hardwareResourceRepository.deleteById(id);
    }
}
