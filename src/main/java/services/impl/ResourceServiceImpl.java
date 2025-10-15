package services.impl;

import entity.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.ResourceRepository;
import services.intf.ResourceServiceIntf;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceServiceIntf {

    private final ResourceRepository resourceRepository;

    @Override
    public List<Resource> getAll() {
        return resourceRepository.findAll();
    }

    @Override
    public Optional<Resource> getById(Long id) {
        return resourceRepository.findById(id);
    }

    @Override
    public Resource save(Resource resource) {
        return resourceRepository.save(resource);
    }

    @Override
    public void delete(Long id) {
        resourceRepository.deleteById(id);
    }
}
