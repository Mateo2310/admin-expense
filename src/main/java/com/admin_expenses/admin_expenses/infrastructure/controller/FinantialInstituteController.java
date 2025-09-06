package com.admin_expenses.admin_expenses.infrastructure.controller;

import com.admin_expenses.admin_expenses.application.dto.FinantialInstituteResponseDTO;
import com.admin_expenses.admin_expenses.application.dto.FinantialInstituteRequestDTO;
import com.admin_expenses.admin_expenses.application.dto.ResponseGeneric;
import com.admin_expenses.admin_expenses.application.service.interfaces.IFinantialInstituteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/finantialInstitute")
@RequiredArgsConstructor
public class FinantialInstituteController {
    private final IFinantialInstituteService iFinantialInstituteService;

    @PostMapping
    public ResponseEntity<?> createFinantialInstitute(@RequestBody FinantialInstituteRequestDTO finantialInstituteRequestDTO) {
        this.iFinantialInstituteService.create(finantialInstituteRequestDTO);
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "finantial institute created successfully", null));
    }

    @GetMapping
    public ResponseEntity<?> getAllFinantialInstitute() {
        List<FinantialInstituteResponseDTO> finantialInstituteRequestDTOList = this.iFinantialInstituteService.findAll();
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Finantials Institute", finantialInstituteRequestDTOList));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteFinantialInstitute(@PathVariable String name) {
        this.iFinantialInstituteService.delete(name);
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "finantial institute deleted successfully", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFinantialInstitute(@PathVariable Long id) {
        FinantialInstituteResponseDTO finantialInstituteResponseDTO = this.iFinantialInstituteService.findById(id);
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Finantial Institute", finantialInstituteResponseDTO));
    }
}
