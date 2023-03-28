package com.example.ecommerceonhtml.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class UserDTO {
    private Long  id;
    private Boolean isActive;
    private String name;
    private String password;
    private String email;
    private String phoneNumber;
}
