package com.dddsample.transaction.domain.order;

import com.dddsample.transaction.domain.Commodity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderItemTest {

    @Test
    void Given_NullCommodity_When_Of_Then_ThrowIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> OrderItem.of(null, 1));
    }

    @Test
    void Given_NegativeOrZeroQuantity_When_Of_Then_ThrowIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> OrderItem.of(Commodity.of("1", "Apple", 8000), 0));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> OrderItem.of(Commodity.of("1", "Apple", 8000), -10));
    }

}