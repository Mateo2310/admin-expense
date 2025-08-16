package com.admin_expenses.admin_expenses.infrastructure.persistence.mapper;

import com.admin_expenses.admin_expenses.domain.model.Role;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.RoleEntity;

import java.util.Date;

public class RoleMapper {
    public static Role toDomainRole(RoleEntity roleEntity) {
        return new Role(roleEntity.getId(), roleEntity.getName(), roleEntity.getCreatedAt(), roleEntity.getUpdatedAt());
    }

    public static RoleEntity toRoleEntity(Role role) {
        return new RoleEntity(role.getId(), role.getName(), role.getCreatedAt(), new Date());
    }
}
