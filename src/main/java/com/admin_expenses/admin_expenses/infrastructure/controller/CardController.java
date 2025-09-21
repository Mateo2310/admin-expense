package com.admin_expenses.admin_expenses.infrastructure.controller;

import com.admin_expenses.admin_expenses.application.dto.CardRequestDTO;
import com.admin_expenses.admin_expenses.application.dto.ResponseGeneric;
import com.admin_expenses.admin_expenses.application.service.interfaces.ICardService;
import com.admin_expenses.admin_expenses.infrastructure.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {
    private final ICardService iCardService;

    @PostMapping
    public ResponseEntity<?> createCard(@Valid @RequestBody CardRequestDTO cardRequestDTO, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        this.iCardService.create(cardRequestDTO, userDetails.getUserModel());
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Card created successfully", null));
    }

    @GetMapping
    public ResponseEntity<?> getAllCards(Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok().body(new  ResponseGeneric<>("success", "Cards", this.iCardService.findAll(userDetails.getUserModel().getId())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCard(@Valid @PathVariable Long id, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        this.iCardService.deleteById(id, userDetails.getUserModel().getId());
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Card deleted successfully", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCard(@Valid @PathVariable Long id, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Card", this.iCardService.findById(id, userDetails.getUserModel().getId())));
    }
}
