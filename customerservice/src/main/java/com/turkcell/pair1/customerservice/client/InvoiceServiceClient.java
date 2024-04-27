package com.turkcell.pair1.customerservice.client;

import com.turkcell.pair1.configuration.feign.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "invoiceservice", configuration = FeignClientConfiguration.class)
public interface InvoiceServiceClient {
    @GetMapping("/api/invoice/accounts/getCustomerIdByAccountNumber/{accountNumber}")
    String getCustomerIdByAccountNumber(@PathVariable String accountNumber);
}