package com.userservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class JwtResponse {
    String token;
    Long id;
    String username;
    String email;
    List<String> roles;
}
