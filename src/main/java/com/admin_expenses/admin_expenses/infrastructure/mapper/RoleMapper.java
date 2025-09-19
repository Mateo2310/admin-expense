package com.admin_expenses.admin_expenses.infrastructure.mapper;

import com.admin_expenses.admin_expenses.domain.model.RoleModel;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.RoleEntity;

import java.util.Date;

public class RoleMapper {
    public static RoleModel toDomainRole(RoleEntity roleEntity) {
        return new RoleModel(roleEntity.getId(), roleEntity.getName());
    }

    public static RoleEntity toRoleEntity(RoleModel roleModel) {
        return new RoleEntity(roleModel.getId(), roleModel.getName(), new Date());
    }
}
