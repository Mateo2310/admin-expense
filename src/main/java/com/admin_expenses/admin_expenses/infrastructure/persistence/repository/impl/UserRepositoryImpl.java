package com.admin_expenses.admin_expenses.infrastructure.persistence.repository.impl;

import com.admin_expenses.admin_expenses.domain.model.UserModel;
import com.admin_expenses.admin_expenses.domain.repository.UserRepository;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.UserEntity;
import com.admin_expenses.admin_expenses.infrastructure.mapper.UserMapper;
import com.admin_expenses.admin_expenses.infrastructure.persistence.repository.interfaces.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final IUserRepository iUserRepository;

    @Override
    public UserModel findById(Long id) {
        return this.iUserRepository.findById(id)
                .map(UserMapper::toDomainUser).orElse(null);
    }

    @Override
    public UserModel save(UserModel userModel) {
        UserEntity userEntity = UserMapper.toUserEntity(userModel);
        UserEntity userSaved = this.iUserRepository.save(userEntity);
        return UserMapper.toDomainUser(userSaved);
    }

    @Override
    public void deleteById(Long id) {
        this.iUserRepository.deleteById(id);
    }

    @Override
    public List<UserModel> findAll() {
        return this.iUserRepository.findAll().stream().map(UserMapper::toDomainUser).collect(Collectors.toList());
    }

    @Override
    public UserModel findByUsername(String username) {
        return this.iUserRepository.findByUsername(username)
                .map(UserMapper::toDomainUser).orElse(null);
    }
}
