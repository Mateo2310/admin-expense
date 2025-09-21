package com.admin_expenses.admin_expenses.infrastructure.controller;

import com.admin_expenses.admin_expenses.application.dto.CardTierRequestDTO;
import com.admin_expenses.admin_expenses.application.dto.ResponseGeneric;
import com.admin_expenses.admin_expenses.application.service.interfaces.ICardTierService;
import com.admin_expenses.admin_expenses.infrastructure.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cardTier")
@RequiredArgsConstructor
public class CardTierController {
    private final ICardTierService cardTierService;

    @PostMapping
    public ResponseEntity<?> createCardTier(@RequestBody CardTierRequestDTO cardTierRequestDTO, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        this.cardTierService.create(cardTierRequestDTO, userDetails.getUserModel());
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Card tier created successfully", null));
    }

    @GetMapping
    public ResponseEntity<?> getAllCardTier(Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Cards", this.cardTierService.findAll(userDetails.getUserModel().getId())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCardTier(@PathVariable Long id, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        this.cardTierService.deleteById(id, userDetails.getUserModel().getId());
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Card tier deleted successfully", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCardTierById(@PathVariable Long id, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Card", this.cardTierService.findById(id, userDetails.getUserModel().getId())));
    }
}
