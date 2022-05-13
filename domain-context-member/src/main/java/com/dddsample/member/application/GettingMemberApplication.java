package com.dddsample.member.application;

import com.dddsample.member.domain.member.Member;
import com.dddsample.member.domain.member.MemberNotFoundException;
import com.dddsample.member.domain.member.MemberId;
import com.dddsample.member.domain.member.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class GettingMemberApplication {
    private final MemberRepository repository;

    public GettingMemberApplication(MemberRepository repository) {
        this.repository = repository;
    }

    public Member.Data getMember(MemberId memberId) {
        return repository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException(memberId)
        ).toData();
    }
}
