package com.admin_expenses.admin_expenses.infrastructure.controller;

import com.admin_expenses.admin_expenses.application.dto.FinantialInstituteResponseDTO;
import com.admin_expenses.admin_expenses.application.dto.FinantialInstituteRequestDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.IFinantialInstituteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/finantialInstitute")
public class FinantialInstituteController {
    private final IFinantialInstituteService iFinantialInstituteService;

    public FinantialInstituteController(IFinantialInstituteService iFinantialInstituteService) {
        this.iFinantialInstituteService = iFinantialInstituteService;
    }

    @PostMapping
    public ResponseEntity<?> createFinantialInstitute(@RequestBody FinantialInstituteRequestDTO finantialInstituteRequestDTO) {
        String finantialInstituteResponse = this.iFinantialInstituteService.create(finantialInstituteRequestDTO);
        if (finantialInstituteResponse != null && !finantialInstituteResponse.equals("ERROR")) {
            return ResponseEntity.ok().body(finantialInstituteResponse);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllFinantialInstitute() {
        List<FinantialInstituteResponseDTO> finantialInstituteRequestDTOList = this.iFinantialInstituteService.findAll();
        return ResponseEntity.ok().body(finantialInstituteRequestDTOList);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteFinantialInstitute(@RequestParam String name) {
        String result = this.iFinantialInstituteService.delete(name);
        if (Objects.equals(result, "DELETED")) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
