package com.dddsample.transaction.application;

import com.dddsample.transaction.domain.order.Order;
import com.dddsample.transaction.domain.order.OrderId;
import com.dddsample.transaction.domain.order.OrderNotFoundException;
import com.dddsample.transaction.domain.order.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GettingOrderApplicationTest {

    private GettingOrderApplication gettingOrderApplication;
    private OrderRepository repository;

    @BeforeEach
    void init() {
        repository = mock(OrderRepository.class);
        gettingOrderApplication = new GettingOrderApplication(repository);
    }

    @Test
    void Given_NotExistOrderId_When_GetOrder_Then_ThrowOrderNotFoundException() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        Assertions.assertThrows(OrderNotFoundException.class,
                () -> gettingOrderApplication.getOrder(OrderId.of(1L)));
    }

    @Test
    void Given_ExistOrderId_When_GetOrder_Then_Success() {
        when(repository.findById(any())).thenReturn(Optional.of(mock(Order.class)));
        gettingOrderApplication.getOrder(OrderId.of(1L));
        verify(repository).findById(OrderId.of(1L));
    }

}