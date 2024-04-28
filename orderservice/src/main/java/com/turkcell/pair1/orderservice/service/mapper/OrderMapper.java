package com.turkcell.pair1.orderservice.service.mapper;

import com.turkcell.pair1.orderservice.entity.Address;
import com.turkcell.pair1.orderservice.entity.Order;
import com.turkcell.pair1.orderservice.entity.OrderItem;
import com.turkcell.pair1.orderservice.service.dto.request.AddOrderItemRequest;
import com.turkcell.pair1.orderservice.service.dto.request.AddServiceAddressRequest;
import com.turkcell.pair1.orderservice.service.dto.request.PlaceOrderRequest;
import com.turkcell.pair1.orderservice.service.dto.response.GetOrderByIdResponse;
import com.turkcell.pair1.orderservice.service.dto.response.GetOrderItemResponse;
import com.turkcell.pair1.orderservice.service.dto.response.GetServiceAddressResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);


    @Mapping(source = "street.streetName",target = "streetName")
    @Mapping(source = "street.city.name",target = "cityName")
    GetServiceAddressResponse getServiceAddressFromAddress(Address address);
    @Mapping(source = "city",target = "street.city.name")
    @Mapping(source = "streetName",target = "street.streetName")
    Address getAddressFromAddAddressRequest(AddServiceAddressRequest addressRequest);

    GetOrderItemResponse getOrderItemResponseFromOrderItem(OrderItem orderItem);

    List<GetOrderItemResponse> getOrderItemListResponseFromOrderItem(List<OrderItem> orderItems);
    @Mapping(source = "id",target = "orderId")
    @Mapping(source = "items",target = "orderItems")
    @Mapping(source = "serviceAddress",target = "address")
    GetOrderByIdResponse getOrderByIdResponseFromOrder(Order order);
    List<GetOrderByIdResponse> getOrderByIdResponseListFromOrderList(List<Order> orders);
    OrderItem getOrderItemFromAddRequest(AddOrderItemRequest addOrderItemRequest);

    List<OrderItem> getOrderItemListFromAddRequest(List<AddOrderItemRequest> requests);


    Order getOrderFromAddRequest(PlaceOrderRequest request);

}
