package net.me.ebanking.dto;

import lombok.Data;
import net.me.ebanking.enums.Role;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;
}
