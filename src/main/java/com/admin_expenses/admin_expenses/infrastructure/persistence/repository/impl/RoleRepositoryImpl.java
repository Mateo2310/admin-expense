package com.admin_expenses.admin_expenses.infrastructure.persistence.repository.impl;

import com.admin_expenses.admin_expenses.domain.model.RoleModel;
import com.admin_expenses.admin_expenses.domain.repository.RoleRepository;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.RoleEntity;
import com.admin_expenses.admin_expenses.infrastructure.mapper.RoleMapper;
import com.admin_expenses.admin_expenses.infrastructure.persistence.repository.interfaces.IRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {
    private final IRoleRepository iRoleRepository;

    @Override
    public RoleModel findById(Long id) {
        return this.iRoleRepository.findById(id).map(RoleMapper::toDomainRole).orElse(null);
    }

    @Override
    public RoleModel save(RoleModel roleModel) {
        RoleEntity roleEntity = RoleMapper.toRoleEntity(roleModel);
        RoleEntity roleEntitySaved = this.iRoleRepository.save(roleEntity);
        return RoleMapper.toDomainRole(roleEntitySaved);
    }

    @Override
    public void deleteById(Long id) {
        this.iRoleRepository.deleteById(id);
    }

    @Override
    public List<RoleModel> findAll() {
        return this.iRoleRepository.findAll().stream().map(RoleMapper::toDomainRole).collect(Collectors.toList());
    }
}
