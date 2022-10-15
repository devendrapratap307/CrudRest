package com.hb.crud.CrudwithThreeLayer.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorObjectFormat {
    private String fieldName;
    private String message;
}
