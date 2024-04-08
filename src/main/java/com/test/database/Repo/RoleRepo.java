package com.test.database.Repo;

import com.test.database.Model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, Integer> {

    Set<RoleEntity> findByAddons_AddonId(int addonId);
}
