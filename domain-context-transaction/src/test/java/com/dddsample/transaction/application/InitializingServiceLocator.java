package com.dddsample.transaction.application;

import com.robustel.adapter.ddd.service.publisher.guava.GuavaEventBusPublisher;
import com.robustel.ddd.service.EventPublisher;
import com.robustel.ddd.service.ServiceLocator;
import com.robustel.ddd.service.UidGenerator;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;

public class InitializingServiceLocator {
    static {
        ServiceLocator.setRegistry(new ServiceLocator.ServiceRegistry() {
            private Map<Class, Object> services = new HashMap<>();

            {
                services.put(UidGenerator.class, mock(UidGenerator.class));
                services.put(EventPublisher.class, new GuavaEventBusPublisher());
            }

            @Override
            public <T> T getService(Class<T> aClass) {
                return (T) services.get(aClass);
            }

            @Override
            public <T> T getBean(String s, Class<T> aClass) {
                return null;
            }
        });
    }

    public static void init() {
        System.out.println("Initializing service locator...");
    }
}
