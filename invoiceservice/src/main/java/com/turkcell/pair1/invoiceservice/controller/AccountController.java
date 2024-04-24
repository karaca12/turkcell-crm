package com.turkcell.pair1.invoiceservice.controller;

import com.turkcell.pair1.invoiceservice.entity.BasketItem;
import com.turkcell.pair1.invoiceservice.service.abstraction.AccountService;
import com.turkcell.pair1.invoiceservice.service.dto.AccountDto;
import com.turkcell.pair1.invoiceservice.service.dto.request.AddItemToBasketRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoice/accounts")
@RequiredArgsConstructor
public class AccountController {
private final AccountService accountService;

    @GetMapping("/{accountId}") // TODO: change this with DTO
    public AccountDto getAccountDtoById(@PathVariable("accountId") Integer accountId){
        return accountService.getAccountDtoById(accountId);
    }

    @GetMapping("/isActive")
    public boolean isAccountActive(@RequestParam("accountId") Integer accountId) {
        return accountService.isActive(accountId);
    }

    @PostMapping("/addBasketItem")
    @ResponseStatus(HttpStatus.OK) // TODO: GLOBAL EXCEPTION HANDLER NEEDED.
    public void addItemToBasket(@RequestBody AddItemToBasketRequest request) {
        accountService.addItemToBasket(request);
    }
}
