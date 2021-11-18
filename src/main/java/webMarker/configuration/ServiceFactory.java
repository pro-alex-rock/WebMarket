package webMarker.configuration;

import webMarker.service.Service;

public class ServiceFactory {
    private static Service service;

    public static Service getInstance() {
        if (service == null) {
            service = new Service();
        }
        return service;
    }

    private ServiceFactory() {
        service = new Service();
    }
}
