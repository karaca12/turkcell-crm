package com.turkcell.pair1.invoiceservice.controller;

import com.turkcell.pair1.invoiceservice.core.business.paging.PageInfo;
import com.turkcell.pair1.invoiceservice.service.abstraction.AccountService;
import com.turkcell.pair1.invoiceservice.service.dto.GetAccountDtoByAccountNumberResponse;
import com.turkcell.pair1.invoiceservice.service.dto.request.AddItemToBasketRequest;
import com.turkcell.pair1.invoiceservice.service.dto.request.ClearBasketRequest;
import com.turkcell.pair1.invoiceservice.service.dto.response.CheckAccountForOrderResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetAccountProductResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetCustomerAccountsResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetDetailedAccountProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoice/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{accountNumber}")
    public GetAccountDtoByAccountNumberResponse getAccountDtoByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
        return accountService.getAccountDtoByAccountNumber(accountNumber);
    }

    @GetMapping("/isActive")
    public boolean isAccountActive(@RequestParam("accountNumber") String accountNumber) {
        return accountService.isActive(accountNumber);
    }

    @PostMapping("/addBasketItem")
    public void addItemToBasket(@RequestBody AddItemToBasketRequest request) {
        accountService.addItemToBasket(request);
    }

    @PostMapping("/clearBasket")
    @ResponseStatus(HttpStatus.OK)
    public void clearBasket(@RequestBody ClearBasketRequest request) {
        accountService.clearBasket(request.getAccountNumber());
    }

    @GetMapping("getCustomerAccountsByCustomerId/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetCustomerAccountsResponse> getCustomerAccountsByCustomerId(@PathVariable String customerId, @RequestParam int page, @RequestParam int size) {
        PageInfo pageInfo = new PageInfo(page, size);
        return accountService.getCustomerAccountsByCustomerId(customerId, pageInfo);
    }

    @GetMapping("getCustomerIdByAccountNumber/{accountNumber}")
    public String getCustomerIdByAccountNumber(@PathVariable String accountNumber) {
        return accountService.getCustomerIdByAccountNumber(accountNumber);
    }

    @GetMapping("/getProducts/{accountNumber}")
    public List<GetAccountProductResponse> getAccountProducts(@PathVariable String accountNumber) {
        return accountService.getProductsForAccount(accountNumber);
    }

    @GetMapping("/getDetailedProduct/{productOfferId}/{orderId}")
    public GetDetailedAccountProductResponse getDetailedAccountProduct(@PathVariable String productOfferId, @PathVariable String orderId) {
        return accountService.getDetailedAccountProduct(productOfferId, orderId);
    }

    @GetMapping("checkIfAccountExistsAndGetAddress/{accountNumber}/{addressId}")
    @ResponseStatus(HttpStatus.OK)
    public CheckAccountForOrderResponse checkIfAccountExistsAndGetAddress(@PathVariable String accountNumber, @PathVariable Integer addressId) {
        return accountService.checkIfAccountExistsAndGetAddress(accountNumber,addressId);
    }

}