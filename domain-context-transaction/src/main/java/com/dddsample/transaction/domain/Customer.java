package com.dddsample.transaction.domain;

import com.robustel.ddd.core.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString(callSuper = true)
public class Customer implements ValueObject {
    private String id;
    private String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Customer of(String id, String name) {
        return new Customer(id, name);
    }
}
