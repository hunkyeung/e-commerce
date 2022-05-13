package com.dddsample.adapter.persistence.mongo.member;

import com.robustel.adapter.persistence.mongodb.core.AbstractRepositoryMongo;
import com.robustel.adapter.persistence.mongodb.core.MongoPageHelper;
import com.dddsample.member.domain.member.Member;
import com.dddsample.member.domain.member.MemberId;
import com.dddsample.member.domain.member.MemberRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositoryMongo extends AbstractRepositoryMongo<Member, MemberId>
        implements MemberRepository {
    protected MemberRepositoryMongo(MongoTemplate mongoTemplate, MongoPageHelper mongoPageHelper) {
        super(mongoTemplate, mongoPageHelper);
    }
}
