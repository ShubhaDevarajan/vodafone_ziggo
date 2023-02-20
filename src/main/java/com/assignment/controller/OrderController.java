package com.assignment.controller;

import com.assignment.dto.GetOrderResponse;
import com.assignment.dto.Order;
import com.assignment.dto.OrderResponse;
import com.assignment.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("APIs to get and create orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/v1/orders")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "getOrders", nickname = "getOrders")
    public List<GetOrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/v1/orders")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "createOrders", nickname = "createOrders")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody Order order) {
        return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.OK);
    }


}

