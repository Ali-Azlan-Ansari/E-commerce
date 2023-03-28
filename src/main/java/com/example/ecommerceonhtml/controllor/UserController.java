package com.example.ecommerceonhtml.controllor;

import com.example.ecommerceonhtml.DTO.UserDTO;
import com.example.ecommerceonhtml.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.save(userDTO));
    }
    @GetMapping("/user")
    public ResponseEntity<List<UserDTO>> findAllUser(){
        return ResponseEntity.ok(userService.findAll());
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }
    @PutMapping("/user/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.update(userDTO,id));
    }
    @DeleteMapping("/user/{id}")
    public void DeleteByIdUser(@PathVariable Long id){
        userService.delete(id);
    }

    @PatchMapping("/user/set-active-user/{id}")
    public ResponseEntity<UserDTO> setActive(@PathVariable Long id){
        return ResponseEntity.ok(userService.setActive(id));
    }
    @PatchMapping("/user/set-deactivate-user/{id}")
    public ResponseEntity<UserDTO> setDeactivate(@PathVariable Long id){
        return ResponseEntity.ok(userService.setDeactivate(id));
    }
}
