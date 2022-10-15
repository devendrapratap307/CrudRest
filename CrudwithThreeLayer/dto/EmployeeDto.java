package com.hb.crud.CrudwithThreeLayer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy")
    private Date dob;

    private double salary;

    private List<Integer> projectList;

//    @JsonIgnore
    private String projectIds;

    private AddressDto address;


}
