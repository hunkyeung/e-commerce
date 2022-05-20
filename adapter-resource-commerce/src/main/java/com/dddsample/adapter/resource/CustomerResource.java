package com.dddsample.adapter.resource;

import com.dddsample.member.application.GettingMemberApplication;
import com.dddsample.member.application.RegisteringMemberApplication;
import com.dddsample.member.domain.member.Member;
import com.dddsample.member.domain.member.MemberId;
import com.dddsample.transaction.application.GettingOrderApplication;
import com.dddsample.transaction.application.PlacingOrderApplication;
import com.dddsample.transaction.domain.order.Order;
import com.dddsample.transaction.domain.order.OrderId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerResource {
    @Autowired
    private RegisteringMemberApplication registeringMemberApplication;
    @Autowired
    private GettingMemberApplication gettingMemberApplication;
    @Autowired
    private GettingOrderApplication gettingOrderApplication;
    @Autowired
    private PlacingOrderApplication placingOrderApplication;

    @PostMapping("/members")
    public MemberId register(@RequestBody RegisteringMemberApplication.Command command) {
        return registeringMemberApplication.register(command);
    }

    @GetMapping("/members/{memberId}")
    public Member.Data getMember(@PathVariable String memberId) {
        return gettingMemberApplication.getMember(MemberId.of(memberId));
    }

    @GetMapping("/orders/{orderId}")
    public Order.Data getOrder(@PathVariable long orderId) {
        return gettingOrderApplication.getOrder(OrderId.of(orderId));
    }

    @PostMapping("/orders")
    public OrderId placeOrder(@RequestBody PlacingOrderApplication.Command command) {
        return placingOrderApplication.doPlaceOrder(command);
    }
}
