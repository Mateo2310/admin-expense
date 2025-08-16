package com.admin_expenses.admin_expenses.infrastructure.persistence.mapper;

import com.admin_expenses.admin_expenses.domain.model.FinantialInstitute;
import com.admin_expenses.admin_expenses.domain.model.User;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.FinantialInstituteEntity;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.UserEntity;

public class FinantialInstituteMapper {
    public static FinantialInstitute toDomainFinantianInstitute(FinantialInstituteEntity finantialInstituteEntity) {
        User userDomain = UserMapper.toDomainUser(finantialInstituteEntity.getCreatedBy());
        return new FinantialInstitute(finantialInstituteEntity.getId(), finantialInstituteEntity.getName(), finantialInstituteEntity.getType(), finantialInstituteEntity.getCreatedAt(), finantialInstituteEntity.getUpdatedAt(), userDomain);
    }

    public static FinantialInstituteEntity toFinantialInstitute(FinantialInstitute finantialInstitute) {
        UserEntity userEntity = UserMapper.toUserEntity(finantialInstitute.getCreatedBy());
        return new FinantialInstituteEntity(finantialInstitute.getId(), finantialInstitute.getName(), finantialInstitute.getType(), finantialInstitute.getCreatedAt(), finantialInstitute.getUpdatedAt(), userEntity);
    }
}
