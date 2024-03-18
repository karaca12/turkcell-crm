package com.turkcell.pair1.customerservice.controller;

import com.turkcell.pair1.customerservice.service.abstraction.StreetService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer/street")
public class StreetController {
    private final StreetService streetService;

    public StreetController(StreetService streetService) {
        this.streetService = streetService;
    }
}
