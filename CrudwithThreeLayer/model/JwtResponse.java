package com.hb.crud.CrudwithThreeLayer.model;

import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter

public class JwtResponse {
    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }
}
