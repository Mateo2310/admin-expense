package com.admin_expenses.admin_expenses.infrastructure.persistence.repository.interfaces;

import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.FinantialInstituteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IFinantialInstitueRepository extends JpaRepository<FinantialInstituteEntity, Long> {
    FinantialInstituteEntity findByName(String name);
    Optional<FinantialInstituteEntity> findByIdAndUserId(Long id, Long userId);
    List<FinantialInstituteEntity> findAllByUserId(Long userId);
    void deleteByIdAndUserId(Long id, Long userId);
}
