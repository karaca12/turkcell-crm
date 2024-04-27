package com.turkcell.pair1.invoiceservice.controller;

import com.turkcell.pair1.invoiceservice.service.abstraction.AccountService;
import com.turkcell.pair1.invoiceservice.service.dto.AccountDto;
import com.turkcell.pair1.invoiceservice.service.dto.request.AddItemToBasketRequest;
import com.turkcell.pair1.invoiceservice.service.dto.request.ClearBasketRequest;
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

    @GetMapping("/{accountId}") // TODO: change this with DTO
    public AccountDto getAccountDtoById(@PathVariable("accountId") Integer accountId) {
        return accountService.getAccountDtoById(accountId);
    }

    @GetMapping("/isActive")
    public boolean isAccountActive(@RequestParam("accountId") Integer accountId) {
        return accountService.isActive(accountId);
    }

    @PostMapping("/addBasketItem")
    public void addItemToBasket(@RequestBody AddItemToBasketRequest request) {
        accountService.addItemToBasket(request);
    }

    @PostMapping("/clearBasket")
    @ResponseStatus(HttpStatus.OK)
    public void clearBasket(@RequestBody ClearBasketRequest request) {
        accountService.clearBasket(request.getAccountId());
    }

    @GetMapping("getCustomerAccountsByCustomerId/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetCustomerAccountsResponse> getCustomerAccountsByCustomerId(@PathVariable String customerId) {
        return accountService.getCustomerAccountsByCustomerId(customerId);
    }

    @GetMapping("getCustomerIdByAccountNumber/{accountNumber}")
    public String getCustomerIdByAccountNumber(@PathVariable String accountNumber) {
        return accountService.getCustomerIdByAccountNumber(accountNumber);
    }

    @GetMapping("/getProducts/{accountId}")
    public List<GetAccountProductResponse> getAccountProducts(@PathVariable int accountId){
        return accountService.getProductsForAccount(accountId);
    }

    @GetMapping("/getDetailedProduct/{productId}/{orderId}")
    public GetDetailedAccountProductResponse getDetailedAccountProduct(@PathVariable int productId,@PathVariable String orderId){
        return accountService.getDetailedAccountProduct(productId,orderId);
    }
}