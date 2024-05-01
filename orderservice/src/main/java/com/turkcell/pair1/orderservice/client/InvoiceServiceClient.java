package com.turkcell.pair1.orderservice.client;

import com.turkcell.pair1.configuration.feign.FeignClientConfiguration;
import com.turkcell.pair1.orderservice.service.dto.response.AddOrderAddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "invoiceservice", configuration = FeignClientConfiguration.class)
public interface InvoiceServiceClient {
    @GetMapping("/api/invoice/accounts/checkIfAccountExistsAndGetAddress/{accountNumber}/{addressId}")
    AddOrderAddressResponse checkIfAccountExistsAndGetAddress(@PathVariable String accountNumber, @PathVariable Integer addressId);
}
