package com.turkcell.pair1.orderservice.service.mapper;

import com.turkcell.pair1.orderservice.entity.Address;
import com.turkcell.pair1.orderservice.entity.Order;
import com.turkcell.pair1.orderservice.entity.OrderItem;
import com.turkcell.pair1.orderservice.service.dto.response.GetOrderByIdResponse;
import com.turkcell.pair1.orderservice.service.dto.response.GetOrderItemResponse;
import com.turkcell.pair1.orderservice.service.dto.response.GetServiceAddressResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);


    @Mapping(source = "street.streetName",target = "streetName")
    @Mapping(source = "street.city.name",target = "cityName")
    GetServiceAddressResponse getServiceAddressFromAddress(Address address);

    GetOrderItemResponse getOrderItemResponseFromOrderItem(OrderItem orderItem);
    @Mapping(source = "id",target = "orderId")
    GetOrderByIdResponse getOrderByIdResponseFromOrder(Order order);

}
