package com.dddsample.transaction.application.service;

import com.dddsample.transaction.domain.Commodity;

import java.util.List;

public interface GettingCommodityService {
    List<Commodity> getCommodities(List<String> commodityIds);

    Commodity getCommodity(String commodityId);
}
