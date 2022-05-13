package com.dddsample.transaction.application;

import com.dddsample.transaction.domain.order.Order;
import com.dddsample.transaction.domain.order.OrderId;
import com.dddsample.transaction.domain.order.OrderRepository;
import com.dddsample.transaction.domain.order.OrderNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GettingOrderApplication {
    private OrderRepository repository;

    public GettingOrderApplication(OrderRepository repository) {
        this.repository = repository;
    }

    public Order.Data getOrder(OrderId orderId) {
        return repository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException(orderId)
        ).toData();
    }
}
