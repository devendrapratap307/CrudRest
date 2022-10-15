package com.hb.crud.CrudwithThreeLayer.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "address")
public class AddressBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "pin")
    private String pin;
}
