package com.admin_expenses.admin_expenses.infrastructure.persistence.repository.impl;

import com.admin_expenses.admin_expenses.domain.model.FinantialInstituteModel;
import com.admin_expenses.admin_expenses.domain.repository.FinantialInstituteRepository;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.FinantialInstituteEntity;
import com.admin_expenses.admin_expenses.infrastructure.mapper.FinantialInstituteMapper;
import com.admin_expenses.admin_expenses.infrastructure.persistence.repository.interfaces.IFinantialInstitueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class FinantialInstituteRepositoryImpl implements FinantialInstituteRepository {

    private final IFinantialInstitueRepository iFinantialInstitueRepository;

    @Override
    public FinantialInstituteModel findById(Long id, Long userId) {
        Optional<FinantialInstituteEntity> finantialInstituteEntityOptional = this.iFinantialInstitueRepository.findByIdAndUserId(id, userId);
        return finantialInstituteEntityOptional.map(FinantialInstituteMapper::toDomainFinantianInstitute).orElse(null);
    }

    @Override
    public FinantialInstituteModel save(FinantialInstituteModel finantialInstituteModel) {

        FinantialInstituteEntity finantialInstituteEntity = FinantialInstituteMapper.toFinantialInstitute(finantialInstituteModel);
        FinantialInstituteEntity finantialInstituteEntitySaved = this.iFinantialInstitueRepository.save(finantialInstituteEntity);
        return FinantialInstituteMapper.toDomainFinantianInstitute(finantialInstituteEntitySaved);
    }

    @Override
    public void deleteById(Long id, Long userId) {
        this.iFinantialInstitueRepository.deleteByIdAndUserId(id, userId);
    }

    @Override
    public List<FinantialInstituteModel> findAll(Long userId) {
        return this.iFinantialInstitueRepository.findAllByUserId(userId)
                .stream().map(FinantialInstituteMapper::toDomainFinantianInstitute).collect(Collectors.toList());
    }
}
