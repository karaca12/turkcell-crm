package com.turkcell.pair1.orderservice.service.mapper;

import com.turkcell.pair1.orderservice.model.Address;
import com.turkcell.pair1.orderservice.model.Order;
import com.turkcell.pair1.orderservice.model.OrderItem;
import com.turkcell.pair1.orderservice.model.ProductSpec;
import com.turkcell.pair1.orderservice.service.dto.request.AddOrderItemRequest;
import com.turkcell.pair1.orderservice.service.dto.request.AddProductSpecRequest;
import com.turkcell.pair1.orderservice.service.dto.request.PlaceOrderRequest;
import com.turkcell.pair1.orderservice.service.dto.response.AddOrderAddressResponse;
import com.turkcell.pair1.orderservice.service.dto.response.GetOrderByIdResponse;
import com.turkcell.pair1.orderservice.service.dto.response.GetOrderItemResponse;
import com.turkcell.pair1.orderservice.service.dto.response.GetServiceAddressResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);


    @Mapping(source = "street.streetName", target = "streetName")
    @Mapping(source = "street.city.name", target = "cityName")
    GetServiceAddressResponse getServiceAddressFromAddress(Address address);

    @Mapping(source = "city", target = "street.city.name")
    @Mapping(source = "street", target = "street.streetName")
    Address getAddressFromAddressResponse(AddOrderAddressResponse addressRequest);

    @Mapping(source = "productSpec", target = "productSpec")
    GetOrderItemResponse getOrderItemResponseFromOrderItem(OrderItem orderItem);

    List<GetOrderItemResponse> getOrderItemListResponseFromOrderItem(List<OrderItem> orderItems);

    @Mapping(source = "id", target = "orderId")
    @Mapping(source = "items", target = "orderItems")
    @Mapping(source = "serviceAddress", target = "address")
    GetOrderByIdResponse getOrderByIdResponseFromOrder(Order order);

    List<GetOrderByIdResponse> getOrderByIdResponseListFromOrderList(List<Order> orders);

    List<OrderItem> getOrderItemListFromAddRequest(List<AddOrderItemRequest> requests);

    @Mappings({
            @Mapping(source = "orderItems", target = "items")
    })
    Order getOrderFromAddRequest(PlaceOrderRequest request);

    OrderItem getOrderItemFromAddRequest(AddOrderItemRequest item);

    ProductSpec productSpecFromAddRequest(AddProductSpecRequest specRequest);
}