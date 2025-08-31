package com.admin_expenses.admin_expenses.infrastructure.persistence.mapper;

import com.admin_expenses.admin_expenses.domain.model.Role;
import com.admin_expenses.admin_expenses.domain.model.User;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.RoleEntity;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.UserEntity;

import java.util.Date;

public class UserMapper {
    public static User toDomainUser(UserEntity userEntity) {
        Role role = RoleMapper.toDomainRole(userEntity.getRoleEntity());
        return new User(userEntity.getId(), userEntity.getName(), userEntity.getUsername(), userEntity.getLastname(), userEntity.getPassword(), role, userEntity.getCreatedAt(), new Date());
    }

    public static UserEntity toUserEntity(User user) {
        RoleEntity roleEntity = RoleMapper.toRoleEntity(user.getRole());
        return new UserEntity(user.getId(), user.getName(), user.getUsername(), user.getLastname(), user.getPassword(), roleEntity, user.getCreatedAt(), new Date());
    }
}
