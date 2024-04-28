package com.turkcell.pair1.productservice.service.dto.request;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class InternetServiceConfiguration implements ProductConfiguration{
    private String pstnNo;

    private String xdslUsername;

    private String bandwidth;

    private String xdslNo;

    private String xdslPassword;
}
