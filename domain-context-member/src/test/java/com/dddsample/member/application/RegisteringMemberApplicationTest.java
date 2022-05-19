package com.dddsample.member.application;

import com.dddsample.member.domain.member.MemberRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class RegisteringMemberApplicationTest {

    private static MemberRepository repository;
    private static RegisteringMemberApplication registeringMemberApplication;

    @BeforeAll
    static void staticInit() {
        repository = mock(MemberRepository.class);
        registeringMemberApplication = new RegisteringMemberApplication(repository);
    }

    @Test
    void Given_Normal_When_Register_Then_Success() {
        registeringMemberApplication.register(new RegisteringMemberApplication.Command("ZhangSan", "Programmer"));
    }

}