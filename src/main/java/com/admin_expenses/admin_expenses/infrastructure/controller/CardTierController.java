package com.admin_expenses.admin_expenses.infrastructure.controller;

import com.admin_expenses.admin_expenses.application.dto.CardTierRequestDTO;
import com.admin_expenses.admin_expenses.application.dto.ResponseGeneric;
import com.admin_expenses.admin_expenses.application.service.interfaces.ICardTierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cardTier")
@RequiredArgsConstructor
public class CardTierController {
    private final ICardTierService cardTierService;

    @PostMapping
    public ResponseEntity<?> createCardTier(@RequestBody CardTierRequestDTO cardTierRequestDTO){
        this.cardTierService.create(cardTierRequestDTO);
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Card tier created successfully", null));
    }

    @GetMapping
    public ResponseEntity<?> getAllCardTier(){
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Cards", this.cardTierService.findAll()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCardTier(@PathVariable Long id){
        this.cardTierService.deleteById(id);
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Card tier deleted successfully", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCardTierById(@PathVariable Long id){
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Card", this.cardTierService.findById(id)));
    }
}
