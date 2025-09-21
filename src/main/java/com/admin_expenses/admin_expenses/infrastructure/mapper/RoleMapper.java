package com.admin_expenses.admin_expenses.infrastructure.mapper;

import com.admin_expenses.admin_expenses.domain.model.RoleModel;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.RoleEntity;

import java.util.Date;

public class RoleMapper {
    public static RoleModel toDomainRole(RoleEntity roleEntity) {
        if (roleEntity==null) return null;
        return new RoleModel(roleEntity.getId(), roleEntity.getName());
    }

    public static RoleEntity toRoleEntity(RoleModel roleModel) {
        if (roleModel==null) return null;
        return new RoleEntity(roleModel.getId(), roleModel.getName(), new Date());
    }
}
