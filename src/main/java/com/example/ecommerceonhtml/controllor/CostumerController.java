package com.example.ecommerceonhtml.controllor;

import com.example.ecommerceonhtml.DTO.CostumerDTO;
import com.example.ecommerceonhtml.service.CostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class CostumerController {
    @Autowired
    CostumerService costumerService;

    @PostMapping("/costumer")
    public ResponseEntity<CostumerDTO> saveUser(@RequestBody CostumerDTO costumerDTO){
        return ResponseEntity.ok(costumerService.save(costumerDTO));
    }
    @GetMapping("/costumer")
    public ResponseEntity<List<CostumerDTO>> findAllUser(){
        return ResponseEntity.ok(costumerService.findAll());
    }
    @GetMapping("/costumer/{id}")
    public ResponseEntity<CostumerDTO> findUserById(@PathVariable Long id){
        return ResponseEntity.ok(costumerService.findById(id));
    }
    @PutMapping("/costumer/{id}")
    public ResponseEntity<CostumerDTO> updateUser(@PathVariable Long id,@RequestBody CostumerDTO costumerDTO){
        return ResponseEntity.ok(costumerService.update(costumerDTO,id));
    }
    @DeleteMapping("/costumer/{id}")
    public void DeleteByIdUser(@PathVariable Long id){
        costumerService.delete(id);
    }

    @PatchMapping("/costumer/set-active-costumer/{id}")
    public ResponseEntity<CostumerDTO> setActive(@PathVariable Long id){
        return ResponseEntity.ok(costumerService.setActive(id));
    }
    @PatchMapping("/costumer/set-deactivate-costumer/{id}")
    public ResponseEntity<CostumerDTO> setDeactivate(@PathVariable Long id){
        return ResponseEntity.ok(costumerService.setDeactivate(id));
    }
}
