package com.dddsample.transaction.application;

import com.dddsample.transaction.application.service.GettingCommodityService;
import com.dddsample.transaction.application.service.GettingCustomerService;
import com.dddsample.transaction.domain.Commodity;
import com.dddsample.transaction.domain.Customer;
import com.dddsample.transaction.domain.order.Order;
import com.dddsample.transaction.domain.order.OrderCreatedEvent;
import com.dddsample.transaction.domain.order.OrderId;
import com.dddsample.transaction.domain.order.OrderRepository;
import com.google.common.eventbus.Subscribe;
import com.robustel.ddd.service.EventPublisher;
import com.robustel.ddd.service.ServiceLocator;
import com.robustel.ddd.service.UidGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

class PlacingOrderApplicationTest {

    private OrderRepository repository;
    private PlacingOrderApplication placingOrderApplication;
    private GettingCustomerService gettingCustomerService;
    private GettingCommodityService gettingCommodityService;

    @BeforeAll
    static void staticInit() {
        InitializingServiceLocator.init();
    }

    @BeforeEach
    void init() {
        repository = mock(OrderRepository.class);
        gettingCustomerService = mock(GettingCustomerService.class);
        when(gettingCustomerService.getCurrentCustomer()).thenReturn(Customer.of("1", "ZhangSan"));
        gettingCommodityService = mock(GettingCommodityService.class);
        when(gettingCommodityService.getCommodities(Arrays.asList("1"))).thenReturn(Arrays.asList(Commodity.of("1", "Apple", 8000)));
        placingOrderApplication = new PlacingOrderApplication(repository, gettingCustomerService, gettingCommodityService);
    }

    @Test
    void Given_EmptyCart_When_PlaceOrder_Then_ThrowIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> placingOrderApplication.doPlaceOrder(new PlacingOrderApplication.Command()));
    }

    @Test
    void Given_CartWithApple_When_PlaceOrder_Then_Success() {
        when(ServiceLocator.service(UidGenerator.class).nextId()).thenReturn(1L);
        class ListenerEvent {
            @Subscribe
            void listener(OrderCreatedEvent event) {
                if (!event.getOrderId().equals(OrderId.of(1L)))
                    fail("Should not receive domain event.");
            }
        }
        ServiceLocator.service(EventPublisher.class).register(new ListenerEvent());
        Map<String, Integer> cart = new HashMap<>();
        cart.put("1", 10);
        placingOrderApplication.doPlaceOrder(new PlacingOrderApplication.Command("shippingAddress", cart));
        verify(gettingCustomerService).getCurrentCustomer();
        verify(gettingCommodityService).getCommodities(anyList());
        verify(repository).save(any(Order.class));
    }

}