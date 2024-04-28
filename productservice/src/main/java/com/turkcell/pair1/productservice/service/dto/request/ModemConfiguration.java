package com.turkcell.pair1.productservice.service.dto.request;

import lombok.Getter;

@Getter
public class ModemConfiguration implements ProductConfiguration{
    private String brand;
    private String serialNumber;
    private String model;
}
