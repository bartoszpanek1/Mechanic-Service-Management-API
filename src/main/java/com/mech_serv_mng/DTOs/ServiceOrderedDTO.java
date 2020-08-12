package com.mech_serv_mng.DTOs;

import lombok.Data;


@Data
public class ServiceOrderedDTO {
    private Double price;

    private String description;

    private String accepted;

    private String deadline;

    private Boolean completed;

    private Integer carId;
}
