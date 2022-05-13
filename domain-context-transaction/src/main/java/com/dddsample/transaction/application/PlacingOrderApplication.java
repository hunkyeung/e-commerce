package com.dddsample.transaction.application;

import com.dddsample.transaction.domain.order.Order;
import com.dddsample.transaction.domain.order.OrderId;
import com.dddsample.transaction.domain.order.OrderRepository;
import com.robustel.ddd.service.EventPublisher;
import com.robustel.ddd.service.ServiceLocator;
import com.dddsample.transaction.application.service.GettingCommodityService;
import com.dddsample.transaction.application.service.GettingCustomerService;
import com.dddsample.transaction.domain.Commodity;
import com.dddsample.transaction.domain.Customer;
import com.dddsample.transaction.domain.order.OrderCreatedEvent;
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

    public PlacingOrderApplication(OrderRepository repository) {
        this.repository = repository;
    }

    public OrderId doPlaceOrder(Command command) {
        //获取客户信息
        Customer customer = ServiceLocator.service(GettingCustomerService.class).getCurrentCustomer();

        //根据购物车获取商品信息
        Map<String, Integer> cart = Optional.ofNullable(command.getCart())
                .orElseThrow(() -> new IllegalArgumentException("Invalid argument. "));
        List<Commodity> commodities =
                ServiceLocator.service(GettingCommodityService.class).getCommodities(cart.keySet().stream().toList());
        Map<Commodity, Integer> commodityAndQuantity =
                commodities.stream().collect(Collectors.toMap(commodity -> commodity, commodity -> cart.get(commodity.getId())));

        //保存订单
        Order order = Order.of(customer, commodityAndQuantity);
        repository.save(order);

        //发布领域事件
        ServiceLocator.service(EventPublisher.class).publish(new OrderCreatedEvent(order.id()));
        return order.id();
    }

    @Getter
    @NoArgsConstructor
    @ToString
    public static class Command {
        private Map<String, Integer> cart;

        public Command(Map<String, Integer> cart) {
            this.cart = cart;
        }
    }
}
