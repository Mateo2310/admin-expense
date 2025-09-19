package com.admin_expenses.admin_expenses.infrastructure.persistence.repository.impl;

import com.admin_expenses.admin_expenses.domain.model.RoleModel;
import com.admin_expenses.admin_expenses.domain.repository.RoleRepository;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.RoleEntity;
import com.admin_expenses.admin_expenses.infrastructure.mapper.RoleMapper;
import com.admin_expenses.admin_expenses.infrastructure.persistence.repository.interfaces.IRoleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    private final IRoleRepository iRoleRepository;

    public RoleRepositoryImpl(IRoleRepository iRoleRepository) {
        this.iRoleRepository = iRoleRepository;
    }

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
    public RoleModel update(RoleModel roleModel) {
        return RoleMapper.toDomainRole(this.iRoleRepository.save(RoleMapper.toRoleEntity(roleModel)));
    }

    @Override
    public void delete(RoleModel roleModel) {
        this.iRoleRepository.delete(RoleMapper.toRoleEntity(roleModel));
    }

    @Override
    public void deleteById(Long id) {
        this.iRoleRepository.deleteById(id);
    }

    @Override
    public List<RoleModel> findAll() {
        return this.iRoleRepository.findAll().stream().map(RoleMapper::toDomainRole).collect(Collectors.toList());
    }

    @Override
    public RoleModel findByUserId(Long userId) {
        return null;
    }

    @Override
    public RoleModel findByName(String name) {
        return this.iRoleRepository.findByName(name).map(RoleMapper::toDomainRole).orElse(null);
    }
}
