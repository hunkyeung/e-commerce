package com.dddsample.adapter.transaction;

import com.robustel.ddd.core.DomainException;
import com.dddsample.transaction.application.service.GettingCommodityService;
import com.dddsample.transaction.domain.Commodity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GettingCommodityServiceImpl implements GettingCommodityService {
    private static final Map<String, Commodity> commodityMap;

    static {
        commodityMap = new HashMap<>();
        commodityMap.put("1", new Commodity("1", "Apple", 800));
        commodityMap.put("2", new Commodity("2", "Pear", 700));
        commodityMap.put("3", new Commodity("3", "Grape", 1800));
    }

    @Override
    public List<Commodity> getCommodities(List<String> commodityIds) {
        if (commodityIds == null || commodityIds.isEmpty()) {
            throw new IllegalArgumentException("Collection of product id could not be empty. ");
        }
        List<Commodity> commodities =
                commodityMap.values()
                        .stream().filter(product -> commodityIds.contains(product.getId())).toList();
        if (commodities.size() != commodityIds.size()) {
            throw new DomainException("There are some products could not be found. ");
        }
        return commodities;
    }

    @Override
    public Commodity getCommodity(String commodityId) {
        throw new IllegalStateException("Not support yet!");
    }
}
