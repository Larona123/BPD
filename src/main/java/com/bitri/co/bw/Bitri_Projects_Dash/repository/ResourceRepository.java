package com.bitri.co.bw.Bitri_Projects_Dash.repository;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Resource;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

//    @Query("SELECT DISTINCT r.resourceName FROM Resource r WHERE r.resourceName LIKE '%Software%' OR r.resourceName LIKE '%Tool%'")
//    List<String> findDistinctSoftwareToolsUsed();
}
