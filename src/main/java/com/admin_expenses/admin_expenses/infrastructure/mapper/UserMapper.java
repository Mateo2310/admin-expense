package com.admin_expenses.admin_expenses.infrastructure.mapper;

import com.admin_expenses.admin_expenses.domain.model.RoleModel;
import com.admin_expenses.admin_expenses.domain.model.UserModel;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.RoleEntity;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.UserEntity;

public class UserMapper {
    public static UserModel toDomainUser(UserEntity userEntity) {
        if (userEntity == null) return null;
        RoleModel roleModel = RoleMapper.toDomainRole(userEntity.getRoleEntity());
        return new UserModel(userEntity.getId(), userEntity.getName(), userEntity.getUsername(), userEntity.getLastname(), userEntity.getPassword(), roleModel);
    }

    public static UserEntity toUserEntity(UserModel userModel) {
        if (userModel == null) return null;
        RoleEntity roleEntity = RoleMapper.toRoleEntity(userModel.getRoleModel());
        return new UserEntity(userModel.getId(), userModel.getName(), userModel.getUsername(), userModel.getLastname(), userModel.getPassword(), roleEntity);
    }
}
