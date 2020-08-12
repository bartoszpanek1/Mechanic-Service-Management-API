package com.mech_serv_mng.DTOs;

import lombok.Data;

@Data
public class CarDTO {
    private String regNum;

    private String brand;

    private String model;

    private String color;

    private Integer customerId;
}
