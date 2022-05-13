package com.dddsample.member.domain.member;

import com.robustel.ddd.core.AbstractIdentity;

public class MemberId extends AbstractIdentity<String> {
    protected MemberId(String value) {
        super(value);
    }

    public static MemberId of(String value) {
        return new MemberId(value);
    }
}
