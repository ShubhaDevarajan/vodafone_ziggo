package com.assignment.service;

import com.assignment.dto.GetOrderResponse;
import com.assignment.dto.Order;
import com.assignment.dto.OrderResponse;
import com.assignment.entity.Orders;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(Order order);

    List<Orders> getAllOrdersOfUser(String userId);

    List<GetOrderResponse> getAllOrders();

    OrderResponse getOrder(Orders orderDao, Order order);
}
