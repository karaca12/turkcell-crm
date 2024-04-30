package com.turkcell.pair1.productservice.client;

import com.turkcell.pair1.configuration.feign.FeignClientConfiguration;
import com.turkcell.pair1.productservice.service.dto.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "invoiceservice", configuration = FeignClientConfiguration.class)
public interface InvoiceServiceClient {
    @GetMapping("/api/accounts/{accountNumber}")
    AccountDto getAccountByAccountNumber(@PathVariable("accountNumber") String accountNumber);
}