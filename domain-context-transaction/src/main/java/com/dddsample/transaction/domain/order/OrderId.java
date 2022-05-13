package com.dddsample.transaction.domain.order;

import com.robustel.ddd.core.AbstractIdentity;

public class OrderId extends AbstractIdentity<Long> {
    protected OrderId(Long value) {
        super(value);
    }

    public static OrderId of(long value) {
        return new OrderId(value);
    }
}
