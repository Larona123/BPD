package repository;

import entity.HardwareResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HardwareResourceRepository extends JpaRepository<HardwareResource, Long> {
}
