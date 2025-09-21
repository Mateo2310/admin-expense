package com.admin_expenses.admin_expenses.infrastructure.persistence.repository.interfaces;

import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
}
