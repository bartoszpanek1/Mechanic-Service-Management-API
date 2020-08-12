package com.mech_serv_mng.DTOs;

import lombok.Data;

@Data
public class FindMatchingServicesDTO {
    private Double priceLow;
    private Double priceHigh;
    private String description;
    private String acceptedDateLow;
    private String acceptedDateHigh;
    private String deadlineLow;
    private String deadlineHigh;
    private Boolean completed;
    private Integer customerId;
}
