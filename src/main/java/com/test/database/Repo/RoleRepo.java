package com.test.database.Repo;

import com.test.database.Model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, Integer> {
}
