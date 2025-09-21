package com.admin_expenses.admin_expenses.infrastructure.controller;

import com.admin_expenses.admin_expenses.application.dto.FinantialInstituteResponseDTO;
import com.admin_expenses.admin_expenses.application.dto.FinantialInstituteRequestDTO;
import com.admin_expenses.admin_expenses.application.dto.ResponseGeneric;
import com.admin_expenses.admin_expenses.application.service.interfaces.IFinantialInstituteService;
import com.admin_expenses.admin_expenses.infrastructure.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/finantialInstitute")
@RequiredArgsConstructor
public class FinantialInstituteController {
    private final IFinantialInstituteService iFinantialInstituteService;

    @PostMapping
    public ResponseEntity<?> createFinantialInstitute(@RequestBody FinantialInstituteRequestDTO finantialInstituteRequestDTO, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        this.iFinantialInstituteService.create(finantialInstituteRequestDTO, userDetails.getUserModel());
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "finantial institute created successfully", null));
    }

    @GetMapping
    public ResponseEntity<?> getAllFinantialInstitute(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<FinantialInstituteResponseDTO> finantialInstituteRequestDTOList = this.iFinantialInstituteService.findAll(userDetails.getUserModel().getId());
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Finantials Institute", finantialInstituteRequestDTOList));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFinantialInstitute(@PathVariable Long id, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        this.iFinantialInstituteService.deleteById(id, userDetails.getUserModel().getId());
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "finantial institute deleted successfully", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFinantialInstitute(@PathVariable Long id, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        FinantialInstituteResponseDTO finantialInstituteResponseDTO = this.iFinantialInstituteService.findById(id, userDetails.getUserModel().getId());
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Finantial Institute", finantialInstituteResponseDTO));
    }
}
