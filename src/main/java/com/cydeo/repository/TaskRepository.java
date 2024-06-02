package com.cydeo.repository;

import com.cydeo.entity.Project;
import com.cydeo.entity.Task;
import com.cydeo.entity.User;
import com.cydeo.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query("select count (task)  from Task task where task.project.projectCode=?1 and task.taskStatus <>'COMPLETE' ")
    int totalNonCompleteTasks(String projectCode);

    @Query("select count (task) from Task task where task.project.projectCode=:projectCode and task.taskStatus = 'COMPLETE' ")
    int totalCompletedTasks(@Param("projectCode") String projectCode);

    List<Task> findAllByProject(Project project);

    List<Task> findAllByTaskStatusIsNotAndAssignedEmployee(Status status, User user);

    List<Task> findAllByTaskStatusAndAssignedEmployee(Status status, User user);


/*
    Query("select e.firstName from Employee e where e.salary = :salary")
    List<String> retireByParam(@Param("salary") Integer parameter);
 */
}