package com.admin_expenses.admin_expenses.infrastructure.controller;

import com.admin_expenses.admin_expenses.application.dto.PurchaseRequestDTO;
import com.admin_expenses.admin_expenses.application.dto.PurchaseResponseDTO;
import com.admin_expenses.admin_expenses.application.dto.ResponseGeneric;
import com.admin_expenses.admin_expenses.application.service.interfaces.IPurchaseService;
import com.admin_expenses.admin_expenses.infrastructure.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {
    private final IPurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<?> createPurchase(@RequestBody PurchaseRequestDTO purchaseRequestDTO, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        this.purchaseService.create(purchaseRequestDTO, userDetails.getUserModel());
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Purchase created successfully", null));
    }

    @GetMapping
    public ResponseEntity<?> getAllPurchase(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<PurchaseResponseDTO> listPurchases = this.purchaseService.findAll(userDetails.getUserModel().getId());
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Purchases", listPurchases));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPurchase(@PathVariable Long id, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        PurchaseResponseDTO purchaseRequestDTO = this.purchaseService.findById(id, userDetails.getUserModel().getId());
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Purchase", purchaseRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePurchase(@PathVariable Long id, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        this.purchaseService.deleteById(id, userDetails.getUserModel().getId());
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Purchase deleted successfully", null));
    }
}
