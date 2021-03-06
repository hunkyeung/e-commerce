package com.dddsample.transaction.application;

import com.dddsample.transaction.application.service.GettingCommodityService;
import com.dddsample.transaction.application.service.GettingCustomerService;
import com.dddsample.transaction.domain.Commodity;
import com.dddsample.transaction.domain.Customer;
import com.dddsample.transaction.domain.order.Order;
import com.dddsample.transaction.domain.order.OrderCreatedEvent;
import com.dddsample.transaction.domain.order.OrderId;
import com.dddsample.transaction.domain.order.OrderRepository;
import com.robustel.ddd.service.EventPublisher;
import com.robustel.ddd.service.ServiceLocator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlacingOrderApplication {
    private final OrderRepository repository;
    private final GettingCustomerService gettingCustomerService;
    private final GettingCommodityService gettingCommodityService;

    public PlacingOrderApplication(OrderRepository repository, GettingCustomerService gettingCustomerService, GettingCommodityService gettingCommodityService) {
        this.repository = repository;
        this.gettingCustomerService = gettingCustomerService;
        this.gettingCommodityService = gettingCommodityService;
    }

    public OrderId doPlaceOrder(Command command) {
        //获取客户信息
        Customer customer = gettingCustomerService.getCurrentCustomer();

        //根据购物车获取商品信息
        Map<String, Integer> cart = Optional.ofNullable(command.getCart())
                .orElseThrow(() -> new IllegalArgumentException("Invalid argument. "));
        List<Commodity> commodities =
                gettingCommodityService.getCommodities(cart.keySet().stream().toList());
        Map<Commodity, Integer> commodityAndQuantity =
                commodities.stream().collect(Collectors.toMap(commodity -> commodity, commodity -> cart.get(commodity.getId())));

        //保存订单
        Order order = Order.of(customer, command.getShippingAddress(), commodityAndQuantity);
        repository.save(order);

        //发布领域事件
        ServiceLocator.service(EventPublisher.class).publish(new OrderCreatedEvent(order.id()));
        return order.id();
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Command {
        private String shippingAddress;
        private Map<String, Integer> cart;
    }
}
