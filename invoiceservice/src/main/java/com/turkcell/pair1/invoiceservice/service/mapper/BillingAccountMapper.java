package com.turkcell.pair1.invoiceservice.service.mapper;

import com.turkcell.pair1.invoiceservice.entity.BillingAccount;
import com.turkcell.pair1.invoiceservice.service.dto.request.CreateBillingAccountRequest;
import com.turkcell.pair1.invoiceservice.service.dto.request.UpdateBillingAccountRequest;
import com.turkcell.pair1.invoiceservice.service.dto.response.CreateBillingAccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BillingAccountMapper {
    BillingAccountMapper INSTANCE= Mappers.getMapper(BillingAccountMapper.class);
//    @Mapping(target = "addressList", ignore = true)
    BillingAccount getBillingAccountFromCreateRequest(CreateBillingAccountRequest request);
    BillingAccount getBillingAccountFromUpdateRequest(UpdateBillingAccountRequest request);
    @Mapping(target = "addressList", ignore = true)
    CreateBillingAccountResponse getCreateBillingAccountResponseFromBillingAccount(BillingAccount savedBillingAccount);
}
