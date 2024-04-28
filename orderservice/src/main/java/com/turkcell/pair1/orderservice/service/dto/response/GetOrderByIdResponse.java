package com.turkcell.pair1.orderservice.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderByIdResponse {
    private String orderId;
    private List<GetOrderItemResponse> OrderItems;
    private GetServiceAddressResponse address;
    private LocalDate serviceStartDate;

}