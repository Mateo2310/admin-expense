package com.admin_expenses.admin_expenses.application.service;

import com.admin_expenses.admin_expenses.application.dto.FinantialInstituteResponseDTO;
import com.admin_expenses.admin_expenses.application.dto.FinantialInstituteRequestDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.IFinantialInstituteService;
import com.admin_expenses.admin_expenses.domain.exception.*;
import com.admin_expenses.admin_expenses.domain.model.FinantialInstitute;
import com.admin_expenses.admin_expenses.domain.model.User;
import com.admin_expenses.admin_expenses.domain.repository.FinantialInstituteRepository;
import com.admin_expenses.admin_expenses.domain.repository.RoleRepository;
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
           User user = this.userRepository.findByUsername(finantialInstituteRequest.getUsernameCreatedBy());
           if (user == null) {
               throw new UserNotFoundException(finantialInstituteRequest.getUsernameCreatedBy());
           }

           FinantialInstitute newFinantialInstitute = new FinantialInstitute();
           newFinantialInstitute.setName(finantialInstituteRequest.getName());
           newFinantialInstitute.setType(finantialInstituteRequest.getType());
           newFinantialInstitute.setCreatedAt(new Date());
           newFinantialInstitute.setUpdatedAt(new Date());
           newFinantialInstitute.setCreatedBy(user);

           this.finantialInstituteRepository.save(newFinantialInstitute);

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
            User userFinded = this.userRepository.findByUsername(dto.getUsernameCreatedBy());
            FinantialInstitute finantialInstituteToSave = this.finantialInstituteRepository.findByName(dto.getName());
            if (userFinded == null) {
                throw new UserNotFoundException(dto.getUsernameCreatedBy());
            }

            if (finantialInstituteToSave == null) {
                throw new FinantialInstituteNotFoundException(dto.getName());
            }

            finantialInstituteToSave.setName(dto.getName());
            finantialInstituteToSave.setType(dto.getType());
            finantialInstituteToSave.setUpdatedAt(new Date());
            this.finantialInstituteRepository.update(finantialInstituteToSave);

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
            FinantialInstitute finantialInstitute = this.finantialInstituteRepository.findById(id);
            FinantialInstituteResponseDTO finantialInstituteResponseDTO = new FinantialInstituteResponseDTO();
            finantialInstituteResponseDTO.setName(finantialInstitute.getName());
            finantialInstituteResponseDTO.setType(finantialInstitute.getType());
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
            List<FinantialInstitute> finantialInstituteList = this.finantialInstituteRepository.findAll();
            List<FinantialInstituteResponseDTO>  finantialInstituteResponseDTOList = new ArrayList<>();

            finantialInstituteList.forEach(finantialInstitute -> {
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
            FinantialInstitute finantialInstitute = this.finantialInstituteRepository.findByName(name);
            if (finantialInstitute == null) {
                throw new FinantialInstituteNotFoundException(name);
            }
            this.finantialInstituteRepository.delete(finantialInstitute);
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
