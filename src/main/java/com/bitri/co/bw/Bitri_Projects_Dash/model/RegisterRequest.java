package com.bitri.co.bw.Bitri_Projects_Dash.model;

import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.Role;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
