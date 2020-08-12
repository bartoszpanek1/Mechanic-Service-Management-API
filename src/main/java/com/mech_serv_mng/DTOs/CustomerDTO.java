package com.mech_serv_mng.DTOs;

import lombok.Data;


@Data
public class CustomerDTO {
    private Integer id;

    private String identNum;

    private String name;

    private String familyName;

    private String phoneNumber;

    private String email;

    private String birthDate;
}
