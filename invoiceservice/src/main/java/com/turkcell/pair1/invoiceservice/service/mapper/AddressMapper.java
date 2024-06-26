package com.turkcell.pair1.invoiceservice.service.mapper;

import com.turkcell.pair1.invoiceservice.entity.Address;
import com.turkcell.pair1.invoiceservice.service.dto.request.AddAddressToAccountRequest;
import com.turkcell.pair1.invoiceservice.service.dto.request.UpdateAddressRequest;
import com.turkcell.pair1.invoiceservice.service.dto.response.CheckAccountForOrderResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.CreateAddressToBillingAccountResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetAddressResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(source = "city", target = "street.city.name")
    @Mapping(source = "streetName", target = "street.name")
    Address addAddressToAccountRequestToAddress(AddAddressToAccountRequest request);

    List<GetAddressResponse> getAddressResponsesFromAddresses(List<Address> addresses);

    Address updateAddressRequestToAddress(UpdateAddressRequest request);

    @Mapping(source = "street.name", target = "street")
    @Mapping(source = "street.city.name", target = "city")
    @Mapping(source = "primary",target = "primary")
    GetAddressResponse getAddressesResponseFromAddress(Address address);

    @Mapping(source = "street.name", target = "street")
    @Mapping(source = "street.city.name", target = "city")
    CreateAddressToBillingAccountResponse getAddressResponseFromAddress(Address address);

    @Mapping(source = "street.name", target = "street")
    @Mapping(source = "street.city.name", target = "city")
    CheckAccountForOrderResponse getAddressResponseForOrderFromAddress(Address address);
}