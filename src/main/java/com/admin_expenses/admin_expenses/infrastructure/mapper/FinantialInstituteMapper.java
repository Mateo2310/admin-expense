package com.admin_expenses.admin_expenses.infrastructure.mapper;

import com.admin_expenses.admin_expenses.domain.model.FinantialInstituteModel;
import com.admin_expenses.admin_expenses.domain.model.UserModel;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.FinantialInstituteEntity;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.UserEntity;

public class FinantialInstituteMapper {
    public static FinantialInstituteModel toDomainFinantianInstitute(FinantialInstituteEntity finantialInstituteEntity) {
        UserModel userModelDomain = UserMapper.toDomainUser(finantialInstituteEntity.getCreatedBy());
        return new FinantialInstituteModel(finantialInstituteEntity.getId(), finantialInstituteEntity.getName(), finantialInstituteEntity.getType(), userModelDomain);
    }

    public static FinantialInstituteEntity toFinantialInstitute(FinantialInstituteModel finantialInstituteModel) {
        UserEntity userEntity = UserMapper.toUserEntity(finantialInstituteModel.getCreatedBy());
        return new FinantialInstituteEntity(finantialInstituteModel.getId(), finantialInstituteModel.getName(), finantialInstituteModel.getType(), userEntity);
    }
}
