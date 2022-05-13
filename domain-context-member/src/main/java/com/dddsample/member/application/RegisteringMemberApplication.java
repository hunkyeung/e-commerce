package com.dddsample.member.application;

import com.dddsample.member.domain.member.Member;
import com.dddsample.member.domain.member.MemberId;
import com.dddsample.member.domain.member.MemberRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Service
public class RegisteringMemberApplication {
    private final MemberRepository repository;

    public RegisteringMemberApplication(MemberRepository repository) {
        this.repository = repository;
    }

    public MemberId register(Command command) {
        Member member = Member.of(command.getName(), command.getDescription());
        repository.save(member);
        return member.id();
    }

    @Getter
    @ToString
    @NoArgsConstructor
    public static class Command {
        private String name;
        private String description;
    }

}
