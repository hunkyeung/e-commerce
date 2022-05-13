package com.dddsample.adapter.persistence.mongo.transaction;

import com.robustel.adapter.persistence.mongodb.core.AbstractRepositoryMongo;
import com.robustel.adapter.persistence.mongodb.core.MongoPageHelper;
import com.dddsample.transaction.domain.order.Order;
import com.dddsample.transaction.domain.order.OrderId;
import com.dddsample.transaction.domain.order.OrderRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryMongo extends AbstractRepositoryMongo<Order, OrderId> implements OrderRepository {
    protected OrderRepositoryMongo(MongoTemplate mongoTemplate, MongoPageHelper mongoPageHelper) {
        super(mongoTemplate, mongoPageHelper);
    }
}
