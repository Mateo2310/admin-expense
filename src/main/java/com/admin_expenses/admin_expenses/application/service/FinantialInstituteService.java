package com.admin_expenses.admin_expenses.application.service;

import com.admin_expenses.admin_expenses.application.dto.FinantialInstituteResponseDTO;
import com.admin_expenses.admin_expenses.application.dto.FinantialInstituteRequestDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.IFinantialInstituteService;
import com.admin_expenses.admin_expenses.domain.model.FinantialInstitute;
import com.admin_expenses.admin_expenses.domain.model.User;
import com.admin_expenses.admin_expenses.domain.repository.FinantialInstituteRepository;
import com.admin_expenses.admin_expenses.domain.repository.RoleRepository;
import com.admin_expenses.admin_expenses.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FinantialInstituteService implements IFinantialInstituteService {
    private final UserRepository userRepository;
    private final FinantialInstituteRepository finantialInstituteRepository;

    public FinantialInstituteService(UserRepository userRepository, RoleRepository roleRepository, FinantialInstituteRepository finantialInstituteRepository) {
        this.userRepository = userRepository;
        this.finantialInstituteRepository = finantialInstituteRepository;
    }

    public String create(FinantialInstituteRequestDTO finantialInstituteRequest) {
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

    @Override
    public String update(FinantialInstituteRequestDTO dto) {
        return "";
    }

    @Override
    public String deleteById(Long id) {
        return "";
    }

    @Override
    public FinantialInstituteResponseDTO findById(Long id) {
        return null;
    }

    public List<FinantialInstituteResponseDTO> findAll(){
        List<FinantialInstitute> finantialInstituteList = this.finantialInstituteRepository.findAll();
        List<FinantialInstituteResponseDTO>  finantialInstituteResponseDTOList = new ArrayList<>();

        finantialInstituteList.forEach(finantialInstitute -> {
           FinantialInstituteResponseDTO finantialInstituteResponseDTO = new FinantialInstituteResponseDTO();
           finantialInstituteResponseDTO.setName(finantialInstitute.getName());
           finantialInstituteResponseDTO.setType(finantialInstitute.getType());
            finantialInstituteResponseDTOList.add(finantialInstituteResponseDTO);
        });

        return finantialInstituteResponseDTOList;
    }

    public String delete(String name) {
        Optional<FinantialInstitute>  finantialInstitute = this.finantialInstituteRepository.findByName(name);
        if (finantialInstitute.isEmpty()) {
            return "Finantial Institute not found";
        }
        finantialInstitute.ifPresent(this.finantialInstituteRepository::delete);
        return "DELETED";
    }
}
