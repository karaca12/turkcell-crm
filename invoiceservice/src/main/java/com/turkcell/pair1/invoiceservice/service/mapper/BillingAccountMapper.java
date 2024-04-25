package com.turkcell.pair1.invoiceservice.service.mapper;

import com.turkcell.pair1.invoiceservice.entity.BillingAccount;
import com.turkcell.pair1.invoiceservice.service.dto.request.CreateBillingAccountRequest;
import com.turkcell.pair1.invoiceservice.service.dto.response.CreateBillingAccountResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetBillingAccountInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BillingAccountMapper {
    BillingAccountMapper INSTANCE = Mappers.getMapper(BillingAccountMapper.class);

    BillingAccount getBillingAccountFromCreateRequest(CreateBillingAccountRequest request);

    GetBillingAccountInfoResponse getBillingAccountInfoFromBillingAccount(BillingAccount billingAccount);

    @Mapping(target = "addressList", ignore = true)
    CreateBillingAccountResponse getCreateBillingAccountResponseFromBillingAccount(BillingAccount savedBillingAccount);
}
