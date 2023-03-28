package com.example.ecommerceonhtml.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ErrorMassage <T>{
   private T body;
   private LocalDateTime dateTime;
}
