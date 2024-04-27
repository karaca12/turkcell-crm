package com.turkcell.pair1.invoiceservice.service.dto.response;

import com.turkcell.pair1.invoiceservice.entity.Address;
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
public class GetAccountOrderResponse {
    private String orderId;
    private List<GetOrderItemResponse> items;
    private List<Address> serviceAddress;
    private LocalDate serviceStartDate;

}