package com.dddsample.transaction.domain.order;

import com.dddsample.transaction.application.InitializingServiceLocator;
import com.dddsample.transaction.domain.Commodity;
import com.dddsample.transaction.domain.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderTest {

    @BeforeAll
    static void staticInit() {
        InitializingServiceLocator.init();
    }

    @Test
    void Given_NullCustomer_When_Of_Then_ThrowIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Order.of(null, new HashMap<>()));
    }

    @Test
    void Given_Normal_When_Of_Then_Success() {
        Customer zhangSan = Customer.of("1", "ZhangSan");
        Commodity apple = Commodity.of("1", "Apple", 8000);
        Commodity pear = Commodity.of("2", "Pear", 5000);
        Order order = Order.of(zhangSan,
                Map.of(apple, 10, pear, 5));
        assertEquals(new Customer("1", "ZhangSan"), order.getCustomer());
        assertTrue(order.getItems().contains(OrderItem.of(Commodity.of("1", "Apple", 8000), 10)));
        assertTrue(order.getItems().contains(OrderItem.of(Commodity.of("2", "Pear", 5000), 5)));
        Order.Data data = new Order.Data(order.id().value(),
                Customer.of("1", "ZhangSan")
                , Set.of(
                OrderItem.of(Commodity.of("1", "Apple", 8000), 10),
                OrderItem.of(Commodity.of("2", "Pear", 5000), 5)),
                8000L * 10 + 5000 * 5);
        assertEquals(data, order.toData());
    }

}