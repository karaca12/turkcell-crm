package com.turkcell.pair1.invoiceservice.service.dto.request;

import com.turkcell.pair1.invoiceservice.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccountRequest {
    private List<Address> addresses;
    private LocalDateTime updatedAt;
}
