package com.admin_expenses.admin_expenses.infrastructure.controller;

import com.admin_expenses.admin_expenses.application.dto.PurchaseRequestDTO;
import com.admin_expenses.admin_expenses.application.dto.PurchaseResponseDTO;
import com.admin_expenses.admin_expenses.application.dto.ResponseGeneric;
import com.admin_expenses.admin_expenses.application.service.interfaces.IPurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {
    private final IPurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<?> createPurchase(@RequestBody PurchaseRequestDTO purchaseRequestDTO){
        this.purchaseService.create(purchaseRequestDTO);
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Purchase created successfully", null));
    }

    @GetMapping
    public ResponseEntity<?> getAllPurchase() {
        List<PurchaseResponseDTO> listPurchases = this.purchaseService.findAll();
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Purchases", listPurchases));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPurchase(@PathVariable Long id){
        PurchaseResponseDTO purchaseRequestDTO = this.purchaseService.findById(id);
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Purchase", purchaseRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePurchase(@PathVariable Long id){
        this.purchaseService.deleteById(id);
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Purchase deleted successfully", null));
    }
}
