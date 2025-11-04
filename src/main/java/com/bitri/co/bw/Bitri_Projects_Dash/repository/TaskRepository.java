package com.bitri.co.bw.Bitri_Projects_Dash.repository;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository <Task, Long> {

    List<Task> findAllByProject_Id(Long projectId);

    Long countByStatus(String status);

    @Query("SELECT COUNT(t) FROM Task t")
    Long countTotalTasks();

    @Query("SELECT COUNT(t) FROM Task t WHERE t.status = 'IN_PROGRESS'")
    Long countTasksInProgress();

    @Query("SELECT COUNT(t) FROM Task t WHERE t.status = 'COMPLETE'")
    Long countTasksComplete();

    @Query("SELECT COUNT(t) FROM Task t WHERE t.status = 'NEW'")
    Long countTasksNew();
}
