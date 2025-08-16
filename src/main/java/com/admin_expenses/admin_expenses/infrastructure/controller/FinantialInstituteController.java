package com.admin_expenses.admin_expenses.infrastructure.controller;

import com.admin_expenses.admin_expenses.application.dto.FinantialInstitueResponseDTO;
import com.admin_expenses.admin_expenses.application.dto.FinantialInstituteRequestDTO;
import com.admin_expenses.admin_expenses.application.service.FinantialInstituteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/finantialInstitute")
public class FinantialInstituteController {
    private final FinantialInstituteService finantialInstituteService;

    public FinantialInstituteController(FinantialInstituteService finantialInstituteService) {
        this.finantialInstituteService = finantialInstituteService;
    }

    @PostMapping
    public ResponseEntity<?> createFinantialInstitute(@RequestBody FinantialInstituteRequestDTO finantialInstituteRequestDTO) {
        String finantialInstituteResponse = this.finantialInstituteService.createFinantialInstitute(finantialInstituteRequestDTO);
        if (finantialInstituteResponse != null && !finantialInstituteResponse.equals("ERROR")) {
            return ResponseEntity.ok().body(finantialInstituteResponse);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllFinantialInstitute() {
        List<FinantialInstitueResponseDTO> finantialInstituteRequestDTOList = this.finantialInstituteService.getAllFinantialInstitute();
        return ResponseEntity.ok().body(finantialInstituteRequestDTOList);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteFinantialInstitute(@RequestParam String name) {
        String result = this.finantialInstituteService.deleteFinantialInstitute(name);
        if (Objects.equals(result, "DELETED")) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
