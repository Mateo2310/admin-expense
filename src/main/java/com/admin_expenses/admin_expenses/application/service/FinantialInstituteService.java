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
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinantialInstituteService implements IFinantialInstituteService {
    private final UserRepository userRepository;
    private final FinantialInstituteRepository finantialInstituteRepository;

    public String create(FinantialInstituteRequestDTO finantialInstituteRequest) {
       try {
           UserModel userModel = this.userRepository.findByUsername(finantialInstituteRequest.getUsernameCreatedBy());
           if (userModel == null) {
               throw new UserNotFoundException(finantialInstituteRequest.getUsernameCreatedBy());
           }

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
    public String update(FinantialInstituteRequestDTO dto) {
        try {
            UserModel userModelFinded = this.userRepository.findByUsername(dto.getUsernameCreatedBy());
            FinantialInstituteModel finantialInstituteModelToSave = this.finantialInstituteRepository.findByName(dto.getName());
            if (userModelFinded == null) {
                throw new UserNotFoundException(dto.getUsernameCreatedBy());
            }

            if (finantialInstituteModelToSave == null) {
                throw new FinantialInstituteNotFoundException(dto.getName());
            }

            finantialInstituteModelToSave.setName(dto.getName());
            finantialInstituteModelToSave.setType(dto.getType());
            this.finantialInstituteRepository.update(finantialInstituteModelToSave);

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
    public String deleteById(Long id) {
        try {
            this.finantialInstituteRepository.deleteById(id);
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
    public FinantialInstituteResponseDTO findById(Long id) {
        try {
            FinantialInstituteModel finantialInstituteModel = this.finantialInstituteRepository.findById(id);
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

    public List<FinantialInstituteResponseDTO> findAll(){
        try {
            List<FinantialInstituteModel> finantialInstituteModelList = this.finantialInstituteRepository.findAll();
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

    public String delete(String name) {
        try {
            FinantialInstituteModel finantialInstituteModel = this.finantialInstituteRepository.findByName(name);
            if (finantialInstituteModel == null) {
                throw new FinantialInstituteNotFoundException(name);
            }
            this.finantialInstituteRepository.delete(finantialInstituteModel);
            return "DELETED";
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar la entidad financiera", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar la entidad financiera", e);
        }
    }
}
