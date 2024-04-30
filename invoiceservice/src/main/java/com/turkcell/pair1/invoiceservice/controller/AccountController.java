package com.turkcell.pair1.invoiceservice.controller;

import com.turkcell.pair1.invoiceservice.core.business.paging.PageInfo;
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

    @GetMapping("/{accountNumber}") // TODO: change this with DTO
    public AccountDto getAccountDtoByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
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

    @GetMapping("/getDetailedProduct/{productId}/{orderId}")
    public GetDetailedAccountProductResponse getDetailedAccountProduct(@PathVariable int productId, @PathVariable String orderId) {
        return accountService.getDetailedAccountProduct(productId, orderId);
    }
}