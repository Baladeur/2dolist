package com.wcs._2dolist.repository;

import com.wcs._2dolist.entity.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {

    List<TaskList> findAllByWorkspaceId(Long workspaceId);
}
