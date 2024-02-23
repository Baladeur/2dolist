package com.wcs._2dolist.repository;

import com.wcs._2dolist.entity.UserWorkspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWorkspaceRepository extends JpaRepository<UserWorkspace, Long> {

}
