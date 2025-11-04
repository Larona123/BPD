package com.bitri.co.bw.Bitri_Projects_Dash.repository;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.HardwareResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HardwareResourceRepository extends JpaRepository<HardwareResource, Long> {
    @Query("SELECT COUNT(h) FROM HardwareResource h")
    Long countTotalEquipment();
}
