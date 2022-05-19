package com.dddsample.member.application;

import com.dddsample.member.domain.member.Member;
import com.dddsample.member.domain.member.MemberId;
import com.dddsample.member.domain.member.MemberNotFoundException;
import com.dddsample.member.domain.member.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GettingMemberApplicationTest {

    private static MemberRepository repository;
    private static GettingMemberApplication gettingMemberApplication;

    @BeforeAll
    static void staticInit() {
        repository = mock(MemberRepository.class);
        gettingMemberApplication = new GettingMemberApplication(repository);
    }


    @Test
    void Given_NotExist_When_GetMember_Then_ThrowIllegalArgumentException() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        Assertions.assertThrows(MemberNotFoundException.class,
                () -> gettingMemberApplication.getMember(MemberId.of("1")));
    }

    @Test
    void Given_Exist_When_GetMember_Then_Success() {
        when(repository.findById(any())).thenReturn(Optional.of(Member.of("ZhangSan", "Programmer")));
        Member.Data member = gettingMemberApplication.getMember(MemberId.of("1"));
        assertEquals(new Member.Data(member.getId(), "ZhangSan", "Programmer"), member);
    }

}