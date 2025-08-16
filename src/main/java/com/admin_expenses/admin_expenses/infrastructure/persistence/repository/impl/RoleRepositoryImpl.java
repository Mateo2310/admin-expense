package com.admin_expenses.admin_expenses.infrastructure.persistence.repository.impl;

import com.admin_expenses.admin_expenses.domain.model.Role;
import com.admin_expenses.admin_expenses.domain.repository.RoleRepository;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.RoleEntity;
import com.admin_expenses.admin_expenses.infrastructure.persistence.mapper.RoleMapper;
import com.admin_expenses.admin_expenses.infrastructure.persistence.repository.interfaces.IRoleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    private final IRoleRepository iRoleRepository;

    public RoleRepositoryImpl(IRoleRepository iRoleRepository) {
        this.iRoleRepository = iRoleRepository;
    }

    @Override
    public Optional<Role> findById(Long id) {
        return this.iRoleRepository.findById(id).map(RoleMapper::toDomainRole);
    }

    @Override
    public Role save(Role role) {
        RoleEntity roleEntity = RoleMapper.toRoleEntity(role);
        RoleEntity roleEntitySaved = this.iRoleRepository.save(roleEntity);
        return RoleMapper.toDomainRole(roleEntitySaved);
    }

    @Override
    public Role update(Role role) {
        return null;
    }

    @Override
    public void delete(Role role) {
        this.iRoleRepository.delete(RoleMapper.toRoleEntity(role));
    }

    @Override
    public void deleteById(Long id) {
        this.iRoleRepository.deleteById(id);
    }

    @Override
    public List<Role> findAll() {
        return this.iRoleRepository.findAll().stream().map(RoleMapper::toDomainRole).collect(Collectors.toList());
    }

    @Override
    public Optional<Role> findByUserId(Long userId) {
        return Optional.empty();
    }

    @Override
    public Optional<Role> findByName(String name) {
        return this.iRoleRepository.findByName(name).map(RoleMapper::toDomainRole);
    }
}
