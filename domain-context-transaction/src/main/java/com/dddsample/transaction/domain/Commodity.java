package com.dddsample.transaction.domain;

import com.robustel.ddd.core.ValueObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class Commodity implements ValueObject {
    private String id;
    private String name;
    private long unitPrice;//分

    public static Commodity of(String id, String name, int unitPrice) {
        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException("ID can not be blank. ");
        }
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Name can not be blank. ");
        }
        if (unitPrice <= 0) {
            throw new IllegalArgumentException("Unit price can not be negative or zero. ");
        }
        return new Commodity(id, name, unitPrice);
    }

    public Commodity(String id, String name, int unitPrice) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public long calcTotalPrice(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException(String.format("Quantity[%s] can not be negative. ", quantity));
        }
        return quantity * this.unitPrice;
    }
}
