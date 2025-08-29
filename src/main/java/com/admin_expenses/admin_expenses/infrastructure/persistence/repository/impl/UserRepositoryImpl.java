package com.admin_expenses.admin_expenses.infrastructure.persistence.repository.impl;

import com.admin_expenses.admin_expenses.domain.model.User;
import com.admin_expenses.admin_expenses.domain.repository.UserRepository;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.UserEntity;
import com.admin_expenses.admin_expenses.infrastructure.persistence.mapper.UserMapper;
import com.admin_expenses.admin_expenses.infrastructure.persistence.repository.interfaces.IUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final IUserRepository iUserRepository;

    public UserRepositoryImpl(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public User findById(Long id) {
        return this.iUserRepository.findById(id)
                .map(UserMapper::toDomainUser).orElse(null);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = UserMapper.toUserEntity(user);
        UserEntity userSaved = this.iUserRepository.save(userEntity);
        return UserMapper.toDomainUser(userSaved);
    }

    @Override
    public User update(User user) {
        return UserMapper.toDomainUser(this.iUserRepository.save(UserMapper.toUserEntity(user)));
    }

    @Override
    public void delete(User user) {
        this.iUserRepository.delete(UserMapper.toUserEntity(user));
    }

    @Override
    public void deleteById(Long id) {
        this.iUserRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return this.iUserRepository.findAll().stream().map(UserMapper::toDomainUser).collect(Collectors.toList());
    }

    @Override
    public User findByUsername(String username) {
        return this.iUserRepository.findByUsername(username)
                .map(UserMapper::toDomainUser).orElse(null);
    }
}
