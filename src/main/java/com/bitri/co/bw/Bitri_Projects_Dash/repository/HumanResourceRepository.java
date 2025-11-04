package com.bitri.co.bw.Bitri_Projects_Dash.repository;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.HumanResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanResourceRepository extends JpaRepository<HumanResource, Long> {

    @Query("SELECT COUNT(h) FROM HumanResource h")
    Long countTotalPersonnel();
}
