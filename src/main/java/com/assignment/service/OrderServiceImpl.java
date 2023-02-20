package com.assignment.service;

import com.assignment.dto.GetOrderResponse;
import com.assignment.dto.Order;
import com.assignment.dto.OrderResponse;
import com.assignment.entity.Orders;
import com.assignment.entity.Users;
import com.assignment.exceptions.OrderNotValidException;
import com.assignment.exceptions.ResourceNotFoundException;
import com.assignment.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderResponse createOrder(Order order) {
        Users users = validateOrderAndGetUser(order);
        Orders orderDao = convertToOrderDao(order);
        orderRepository.save(orderDao);
        userService.save(users);
        return getOrder(orderDao, order);
    }

    @Override
    public List<Orders> getAllOrdersOfUser(String email) {
        return orderRepository.findByEmail(email);
    }

    @Override
    public List<GetOrderResponse> getAllOrders() {
        List<GetOrderResponse> getOrderResponseList = new ArrayList<>();
        List<Orders> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            throw new ResourceNotFoundException("Order");
        }
        Map<String, Users> emailToUsers =
                userService.getAllUsers(orders.stream().map(Orders::getEmail).collect(Collectors.toList())).stream().collect(Collectors.toMap(Users::getEmail, Function.identity()));
        for (Orders order : orders) {
            GetOrderResponse getOrderResponse = new GetOrderResponse();
            getOrderResponse.setEmail(order.getEmail());
            getOrderResponse.setOrderId(order.getOrderId());
            getOrderResponse.setProductId(order.getProductId());
            getOrderResponse.setFirstname(emailToUsers.get(order.getEmail()).getFirstname());
            getOrderResponse.setLastName(emailToUsers.get(order.getEmail()).getLastName());
            getOrderResponseList.add(getOrderResponse);
        }
        return getOrderResponseList;
    }

    @Override
    public OrderResponse getOrder(Orders orderDao, Order order) {
        Optional<Orders> orderFromDb = orderRepository.findById(orderDao.getOrderId());
        if (!orderFromDb.isPresent()) {
            throw new ResourceNotFoundException("Order");
        }
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(orderFromDb.get().getOrderId());
        orderResponse.setOrder(convertToOrder(orderFromDb.get(), order));
        return orderResponse;
    }

    public Users validateOrderAndGetUser(Order order) {
        if (order.getProductId() == null || order.getProductId().trim().isEmpty()) {
            throw new OrderNotValidException("Product ID should not be empty");
        }
        List<Orders> ordersOfUser = getAllOrdersOfUser(order.getEmail());
        ordersOfUser.forEach(each -> {
            if (each.getProductId().equals(order.getProductId())) {
                throw new OrderNotValidException("User has already ordered this product");
            }
        });
        return userService.getUser(order);
    }

    private Orders convertToOrderDao(Order order) {
        Orders orderDao = new Orders();
        orderDao.setProductId(order.getProductId());
        orderDao.setEmail(order.getEmail());
        return orderDao;
    }

    private Order convertToOrder(Orders orderDao, Order order) {
        Order responseOrder = new Order();
        responseOrder.setEmail(order.getEmail());
        responseOrder.setProductId(orderDao.getProductId());
        return responseOrder;
    }
}
