# 前言
基于电商场景来示范代码分层架构以及模块之间如果依赖，解耦

# 电商
## 启动
e-commerce-bootstrap

## 交易上下文
domain-context-transaction
### 交易上下文mongo持久适配器
adapter-persistence-mongo-transaction
### 交易上下文依赖会员上下文防腐适配器
adapter-acl-transaction
## 会员上下文
domain-context-member
### 会员上下文mongo持久适配器
adapter-persistence-mongo-member



