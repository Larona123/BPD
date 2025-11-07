package com.bitri.co.bw.Bitri_Projects_Dash.repository;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.HardwareResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HardwareResourceRepository extends JpaRepository<HardwareResource, Long> {
    List<HardwareResource> findAllByProjectId(Long projectId);
    @Query("SELECT COUNT(h) FROM HardwareResource h")
    Long countTotalEquipment();
}
