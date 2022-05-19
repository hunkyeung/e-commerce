package com.dddsample.transaction.domain.order;

import com.dddsample.transaction.domain.Commodity;
import com.dddsample.transaction.domain.Customer;
import com.robustel.ddd.core.AbstractEntity;
import com.robustel.ddd.core.ValueObject;
import com.robustel.ddd.service.ServiceLocator;
import com.robustel.ddd.service.UidGenerator;
import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Order extends AbstractEntity<OrderId> {
    private Customer customer;
    private Set<OrderItem> items;
    private long totalPrice;

    public Order(OrderId id, Customer customer, Set<OrderItem> items, long totalPrice) {
        super(id);
        this.customer = customer;
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public static Order of(Customer customer, Map<Commodity, Integer> commodityAndQuantity) {
        if (Objects.isNull(customer)) {
            throw new IllegalArgumentException("Customer could not be null. ");
        }
        Set<OrderItem> items = Optional.ofNullable(commodityAndQuantity).orElse(new HashMap<>(0))
                .entrySet().stream().filter(entry -> entry.getValue() > 0).map(
                        entry -> OrderItem.of(entry.getKey(), entry.getValue())).collect(Collectors.toSet());
        long totalPrice = items.stream().map(OrderItem::calcTotalPrice)
                .reduce(Long::sum).orElse(0L);
        return new Order(OrderId.of(ServiceLocator.service(UidGenerator.class).nextId()),
                customer, items, totalPrice);
    }

    public Data toData() {
        return new Data(id().value(), customer, items, totalPrice);
    }

    @AllArgsConstructor
    @Getter
    @ToString
    @EqualsAndHashCode
    public static class Data {
        private long id;
        private Customer customer;
        private Set<OrderItem> items;
        private long totalPrice;
    }
}

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@ToString(callSuper = true)
class OrderItem implements ValueObject {
    private Commodity commodity;
    private int quantity;

    static OrderItem of(Commodity commodity, Integer quantity) {
        if (Objects.isNull(commodity)) {
            throw new IllegalArgumentException("Commodity can not be null. ");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity can not be negative or zero. ");
        }
        return new OrderItem(commodity, quantity);
    }

    OrderItem(Commodity commodity, Integer quantity) {
        this.commodity = commodity;
        this.quantity = quantity;
    }

    long calcTotalPrice() {
        return commodity.calcTotalPrice(this.quantity);
    }
}

