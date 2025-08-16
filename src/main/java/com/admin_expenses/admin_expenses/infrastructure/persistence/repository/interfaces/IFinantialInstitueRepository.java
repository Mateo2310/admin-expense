package com.admin_expenses.admin_expenses.infrastructure.persistence.repository.interfaces;

import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.FinantialInstituteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFinantialInstitueRepository extends JpaRepository<FinantialInstituteEntity, Long> {
    FinantialInstituteEntity findByName(String name);
}
