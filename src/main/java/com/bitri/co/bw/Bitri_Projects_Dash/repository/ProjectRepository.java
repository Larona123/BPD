package com.bitri.co.bw.Bitri_Projects_Dash.repository;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Project;
import com.bitri.co.bw.Bitri_Projects_Dash.model.ProjectStatusCountResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findById(Long id);

    @Query("SELECT new com.bitri.co.bw.Bitri_Projects_Dash.model.ProjectStatusCountResponse(p.status, COUNT(p)) " +
            "FROM Project p " +
            "GROUP BY p.status")
    List<ProjectStatusCountResponse> countProjectsByStatus();

//    @Query("SELECT SUM(b.actualExpenditure) FROM Project p JOIN p.budget b")
//    Double calculateTotalExpenditure();
}
