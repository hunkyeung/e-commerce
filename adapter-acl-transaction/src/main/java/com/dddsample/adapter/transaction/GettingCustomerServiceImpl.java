package com.dddsample.adapter.transaction;

import com.dddsample.member.application.GettingMemberApplication;
import com.dddsample.member.domain.member.Member;
import com.dddsample.member.domain.member.MemberId;
import com.dddsample.transaction.application.service.GettingCustomerService;
import com.dddsample.transaction.domain.Customer;
import com.robustel.ddd.service.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class GettingCustomerServiceImpl implements GettingCustomerService {
    @Value("${robustel.user-id}")
    private String user_id;
    private final GettingMemberApplication gettingMemberApplication;

    public GettingCustomerServiceImpl(GettingMemberApplication gettingMemberApplication) {
        this.gettingMemberApplication = gettingMemberApplication;
    }

    @Override
    public Customer getCurrentCustomer() {
        Member.Data data = gettingMemberApplication.getMember(MemberId.of(ThreadLocalUtil.get(user_id)));
        if (Objects.isNull(data)) {
            throw new IllegalStateException("Not found customer. ");
        }
        return Customer.of(data.getId(), data.getName());
    }
}
