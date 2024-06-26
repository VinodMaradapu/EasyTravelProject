package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.travel.bean.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	boolean existsByRoleName(String roleName);

}
