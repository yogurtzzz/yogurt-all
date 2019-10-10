package service;

import anno.AdminOnly;
import anno.Yogurt;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class MassageService implements SomeService {
    @Override
    public void doService() {
        System.out.println("Massage service");
    }

    @Override
    public void doService(Args args) {
        System.out.println("Massage service with args");
    }

    @Override
    public void doService(Long l) {
        System.out.println("Massage service with long "+l);
    }
}
