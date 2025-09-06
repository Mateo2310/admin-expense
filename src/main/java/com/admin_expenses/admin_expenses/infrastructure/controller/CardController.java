package com.admin_expenses.admin_expenses.infrastructure.controller;

import com.admin_expenses.admin_expenses.application.dto.CardRequestDTO;
import com.admin_expenses.admin_expenses.application.dto.ResponseGeneric;
import com.admin_expenses.admin_expenses.application.service.interfaces.ICardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {
    private final ICardService iCardService;

    @PostMapping
    public ResponseEntity<?> createCard(@Valid @RequestBody CardRequestDTO cardRequestDTO){
        this.iCardService.create(cardRequestDTO);
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Card created successfully", null));
    }

    @GetMapping
    public ResponseEntity<?> getAllCards(){
        return ResponseEntity.ok().body(new  ResponseGeneric<>("success", "Cards", this.iCardService.findAll()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCard(@Valid @PathVariable Long id){
        this.iCardService.deleteById(id);
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Card deleted successfully", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCard(@Valid @PathVariable Long id){
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Card", iCardService.findById(id)));
    }
}
