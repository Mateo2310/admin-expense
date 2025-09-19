package com.admin_expenses.admin_expenses.infrastructure.persistence.repository.impl;

import com.admin_expenses.admin_expenses.domain.model.FinantialInstituteModel;
import com.admin_expenses.admin_expenses.domain.repository.FinantialInstituteRepository;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.FinantialInstituteEntity;
import com.admin_expenses.admin_expenses.infrastructure.mapper.FinantialInstituteMapper;
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
    public FinantialInstituteModel findById(Long id) {
        Optional<FinantialInstituteEntity> finantialInstituteEntityOptional = this.iFinantialInstitueRepository.findById(id);
        return finantialInstituteEntityOptional.map(FinantialInstituteMapper::toDomainFinantianInstitute).orElse(null);
    }

    @Override
    public FinantialInstituteModel findByUserId(Long userId) {
        return null;
    }

    @Override
    public FinantialInstituteModel save(FinantialInstituteModel finantialInstituteModel) {

        FinantialInstituteEntity finantialInstituteEntity = FinantialInstituteMapper.toFinantialInstitute(finantialInstituteModel);
        FinantialInstituteEntity finantialInstituteEntitySaved = this.iFinantialInstitueRepository.save(finantialInstituteEntity);
        return FinantialInstituteMapper.toDomainFinantianInstitute(finantialInstituteEntitySaved);
    }

    @Override
    public FinantialInstituteModel update(FinantialInstituteModel finantialInstituteModel) {
        return FinantialInstituteMapper.toDomainFinantianInstitute(this.iFinantialInstitueRepository.save(FinantialInstituteMapper.toFinantialInstitute(finantialInstituteModel)));
    }

    @Override
    public void delete(FinantialInstituteModel finantialInstituteModel) {
        this.iFinantialInstitueRepository.delete(FinantialInstituteMapper.toFinantialInstitute(finantialInstituteModel));
    }

    @Override
    public void deleteById(Long id) {
        this.iFinantialInstitueRepository.deleteById(id);
    }

    @Override
    public List<FinantialInstituteModel> findAll() {
        return this.iFinantialInstitueRepository.findAll()
                .stream().map(FinantialInstituteMapper::toDomainFinantianInstitute).collect(Collectors.toList());
    }

    @Override
    public FinantialInstituteModel findByName(String name) {
        FinantialInstituteEntity finantialInstituteEntity = this.iFinantialInstitueRepository.findByName(name);
        return FinantialInstituteMapper.toDomainFinantianInstitute(finantialInstituteEntity);
    }
}
