package com.bitri.co.bw.Bitri_Projects_Dash.repository;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.beans.JavaBean;
import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Optional<Budget> findByProject_Id(Long projectId);
    List<Budget> findAllByProject_Id(Long projectId);

    // @Query("SELECT b FROM Budget b WHERE b.project.id = :projectId")
    // List<Budget> findByProjectIdCustom(@Param("projectId") Long projectId);
}
