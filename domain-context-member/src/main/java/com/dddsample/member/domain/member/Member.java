package com.dddsample.member.domain.member;

import com.robustel.ddd.core.AbstractEntity;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Member extends AbstractEntity<MemberId> {
    private String name;
    private String description;

    public Member(MemberId id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    public static Member of(String name, String description) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Name could not be blank. ");
        }
        return new Member(MemberId.of(UUID.randomUUID().toString()), name, description);
    }

    public Data toData() {
        return new Data(id().value(), name, description);
    }

    @Getter
    @ToString
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class Data {
        private String id;
        private String name;
        private String description;
    }
}
