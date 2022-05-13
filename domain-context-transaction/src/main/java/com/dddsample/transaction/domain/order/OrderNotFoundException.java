package com.dddsample.transaction.domain.order;

import com.robustel.ddd.core.DomainException;

public class OrderNotFoundException extends DomainException {
    public OrderNotFoundException(OrderId orderId) {
        super(String.format("Not found the order[%s]. ", orderId));
    }
}
