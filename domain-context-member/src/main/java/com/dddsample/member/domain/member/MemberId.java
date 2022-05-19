package com.dddsample.member.domain.member;

import com.robustel.ddd.core.AbstractIdentity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class MemberId extends AbstractIdentity<String> {
    protected MemberId(String value) {
        super(value);
    }

    public static MemberId of(String value) {
        return new MemberId(value);
    }
}
