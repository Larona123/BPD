package repository;

import entity.HumanResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HumanResourceRepository extends JpaRepository<HumanResource, Long> {
}
