package com.bookshop.bookshop.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponseDto {
    private String tokenBody;
    private String tokenType = "Bearer Token";
}