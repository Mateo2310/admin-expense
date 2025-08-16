package com.admin_expenses.admin_expenses.application.service;

import com.admin_expenses.admin_expenses.application.dto.FinantialInstitueResponseDTO;
import com.admin_expenses.admin_expenses.application.dto.FinantialInstituteRequestDTO;
import com.admin_expenses.admin_expenses.domain.model.FinantialInstitute;
import com.admin_expenses.admin_expenses.domain.model.User;
import com.admin_expenses.admin_expenses.domain.repository.FinantialInstituteRepository;
import com.admin_expenses.admin_expenses.domain.repository.RoleRepository;
import com.admin_expenses.admin_expenses.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FinantialInstituteService {
    private final UserRepository userRepository;
    private final FinantialInstituteRepository finantialInstituteRepository;

    public FinantialInstituteService(UserRepository userRepository, RoleRepository roleRepository, FinantialInstituteRepository finantialInstituteRepository) {
        this.userRepository = userRepository;
        this.finantialInstituteRepository = finantialInstituteRepository;
    }

    public String createFinantialInstitute(FinantialInstituteRequestDTO finantialInstituteRequest) {
        Optional<User> user = this.userRepository.findByUsername(finantialInstituteRequest.getUsernameCreatedBy());
        if(user.isEmpty()) {
            throw new IllegalArgumentException("El usuario con mail '" + finantialInstituteRequest.getUsernameCreatedBy() + "' especificado no existe.");
        }

        FinantialInstitute newFinantialInstitute = new FinantialInstitute();
        newFinantialInstitute.setName(finantialInstituteRequest.getName());
        newFinantialInstitute.setType(finantialInstituteRequest.getType());
        newFinantialInstitute.setCreatedAt(new Date());
        newFinantialInstitute.setUpdatedAt(new Date());
        newFinantialInstitute.setCreatedBy(user.get());

        FinantialInstitute savedFinantialInstitute = this.finantialInstituteRepository.save(newFinantialInstitute);

        if (savedFinantialInstitute.getId() != null) {
            return "CREADO";
        } else {
            return "ERROR";
        }
    }

    public List<FinantialInstitueResponseDTO> getAllFinantialInstitute(){
        List<FinantialInstitute> finantialInstituteList = this.finantialInstituteRepository.findAll();
        List<FinantialInstitueResponseDTO>  finantialInstituteResponseDTOList = new ArrayList<>();

        finantialInstituteList.forEach(finantialInstitute -> {
           FinantialInstitueResponseDTO  finantialInstituteResponseDTO = new FinantialInstitueResponseDTO();
           finantialInstituteResponseDTO.setName(finantialInstitute.getName());
           finantialInstituteResponseDTO.setType(finantialInstitute.getType());
            finantialInstituteResponseDTOList.add(finantialInstituteResponseDTO);
        });

        return finantialInstituteResponseDTOList;
    }

    public String deleteFinantialInstitute(String name) {
        Optional<FinantialInstitute>  finantialInstitute = this.finantialInstituteRepository.findByName(name);
        if (finantialInstitute.isEmpty()) {
            return "Finantial Institute not found";
        }
        finantialInstitute.ifPresent(this.finantialInstituteRepository::delete);
        return "DELETED";
    }
}
