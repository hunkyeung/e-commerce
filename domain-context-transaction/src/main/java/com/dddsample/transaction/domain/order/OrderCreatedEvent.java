package com.dddsample.transaction.domain.order;

import com.robustel.ddd.core.AbstractEvent;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class OrderCreatedEvent extends AbstractEvent {
    private final OrderId orderId;

    public OrderCreatedEvent(OrderId orderId) {
        this.orderId = orderId;
    }
}
