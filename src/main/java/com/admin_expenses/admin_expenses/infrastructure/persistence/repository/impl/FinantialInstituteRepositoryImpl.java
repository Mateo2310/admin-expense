package com.admin_expenses.admin_expenses.infrastructure.persistence.repository.impl;

import com.admin_expenses.admin_expenses.domain.model.FinantialInstitute;
import com.admin_expenses.admin_expenses.domain.repository.FinantialInstituteRepository;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.FinantialInstituteEntity;
import com.admin_expenses.admin_expenses.infrastructure.persistence.mapper.FinantialInstituteMapper;
import com.admin_expenses.admin_expenses.infrastructure.persistence.repository.interfaces.IFinantialInstitueRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class FinantialInstituteRepositoryImpl implements FinantialInstituteRepository {

    private final IFinantialInstitueRepository iFinantialInstitueRepository;

    public FinantialInstituteRepositoryImpl(IFinantialInstitueRepository iFinantialInstitueRepository) {
        this.iFinantialInstitueRepository = iFinantialInstitueRepository;
    }

    @Override
    public Optional<FinantialInstitute> findById(Long id) {
        return this.iFinantialInstitueRepository.findById(id).map(FinantialInstituteMapper::toDomainFinantianInstitute);
    }

    @Override
    public Optional<FinantialInstitute> findByUserId(Long userId) {
        return Optional.empty();
    }

    @Override
    public FinantialInstitute save(FinantialInstitute finantialInstitute) {

        FinantialInstituteEntity finantialInstituteEntity = FinantialInstituteMapper.toFinantialInstitute(finantialInstitute);
        FinantialInstituteEntity finantialInstituteEntitySaved = this.iFinantialInstitueRepository.save(finantialInstituteEntity);
        return FinantialInstituteMapper.toDomainFinantianInstitute(finantialInstituteEntitySaved);
    }

    @Override
    public FinantialInstitute update(FinantialInstitute finantialInstitute) {
        return null;
    }

    @Override
    public void delete(FinantialInstitute finantialInstitute) {
        this.iFinantialInstitueRepository.delete(FinantialInstituteMapper.toFinantialInstitute(finantialInstitute));
    }

    @Override
    public void deleteById(Long id) {
        this.iFinantialInstitueRepository.deleteById(id);
    }

    @Override
    public List<FinantialInstitute> findAll() {
        return this.iFinantialInstitueRepository.findAll()
                .stream().map(FinantialInstituteMapper::toDomainFinantianInstitute).collect(Collectors.toList());
    }

    @Override
    public Optional<FinantialInstitute> findByName(String name) {
        FinantialInstituteEntity finantialInstituteEntity = this.iFinantialInstitueRepository.findByName(name);
        return Optional.of(FinantialInstituteMapper.toDomainFinantianInstitute(finantialInstituteEntity));
    }
}
