package com.admin_expenses.admin_expenses.application.service;

import com.admin_expenses.admin_expenses.application.dto.FinantialInstituteResponseDTO;
import com.admin_expenses.admin_expenses.application.dto.FinantialInstituteRequestDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.IFinantialInstituteService;
import com.admin_expenses.admin_expenses.domain.exception.*;
import com.admin_expenses.admin_expenses.domain.model.FinantialInstituteModel;
import com.admin_expenses.admin_expenses.domain.model.UserModel;
import com.admin_expenses.admin_expenses.domain.repository.FinantialInstituteRepository;
import com.admin_expenses.admin_expenses.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinantialInstituteService implements IFinantialInstituteService {
    private final FinantialInstituteRepository finantialInstituteRepository;

    @Override
    public String create(FinantialInstituteRequestDTO finantialInstituteRequest, UserModel userModel) {
       try {
           FinantialInstituteModel newFinantialInstituteModel = new FinantialInstituteModel();
           newFinantialInstituteModel.setName(finantialInstituteRequest.getName());
           newFinantialInstituteModel.setType(finantialInstituteRequest.getType());
           newFinantialInstituteModel.setCreatedBy(userModel);

           this.finantialInstituteRepository.save(newFinantialInstituteModel);

           return "SUCCESS";
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar la entidad financiera", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
           throw new UnexpectedException("Error inesperado al guardar la entidad financiera", e);
       }
    }

    @Override
    public String deleteById(Long id, Long userId) {
        try {
            this.finantialInstituteRepository.deleteById(id, userId);
            return "SUCCESS";
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar la tarjeta", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar la tarjeta", e);
        }
    }

    @Override
    public FinantialInstituteResponseDTO findById(Long id, Long userId) {
        try {
            FinantialInstituteModel finantialInstituteModel = this.finantialInstituteRepository.findById(id, userId);
            FinantialInstituteResponseDTO finantialInstituteResponseDTO = new FinantialInstituteResponseDTO();
            finantialInstituteResponseDTO.setName(finantialInstituteModel.getName());
            finantialInstituteResponseDTO.setType(finantialInstituteModel.getType());
            return finantialInstituteResponseDTO;
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar la entidad financiera", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar la entidad financiera", e);
        }
    }

    @Override
    public List<FinantialInstituteResponseDTO> findAll(Long userId) {
        try {
            List<FinantialInstituteModel> finantialInstituteModelList = this.finantialInstituteRepository.findAll(userId);
            List<FinantialInstituteResponseDTO>  finantialInstituteResponseDTOList = new ArrayList<>();

            finantialInstituteModelList.forEach(finantialInstitute -> {
                FinantialInstituteResponseDTO finantialInstituteResponseDTO = new FinantialInstituteResponseDTO();
                finantialInstituteResponseDTO.setName(finantialInstitute.getName());
                finantialInstituteResponseDTO.setType(finantialInstitute.getType());
                finantialInstituteResponseDTOList.add(finantialInstituteResponseDTO);
            });

            return finantialInstituteResponseDTOList;
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar la entidad financiera", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar la entidad financiera", e);
        }
    }
}
