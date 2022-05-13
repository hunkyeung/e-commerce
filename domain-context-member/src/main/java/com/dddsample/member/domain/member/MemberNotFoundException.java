package com.dddsample.member.domain.member;

import com.robustel.ddd.core.DomainException;

public class MemberNotFoundException extends DomainException {
    public MemberNotFoundException(MemberId memberId) {
        super("Not found the member[" + memberId + "]. ");
    }
}
