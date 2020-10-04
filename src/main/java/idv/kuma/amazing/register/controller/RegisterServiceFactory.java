package idv.kuma.amazing.register.controller;

import idv.kuma.amazing.register.service.*;
import org.springframework.stereotype.Component;

@Component
public class RegisterServiceFactory {

    public RegisterService create(Type type) throws RegisterServiceFactoryException {

        if (type == null) {
            throw new RegisterServiceFactoryException("Type unprovided.");
        }

        switch (type) {
            case EMAIL:
                return new RegisterService(
                        new DataChecker(),
                        new EmailRegisterer(),
                        new TokenGenerator(),
                        new EmailMessenger()
                );
            case PHONE:
                return new RegisterService(
                        new DataChecker(),
                        new PhoneRegisterer(),
                        new TokenGenerator(),
                        new PhoneMessenger()
                );
            default:
                throw new RegisterServiceFactoryException("Type unsupported.");
        }


    }
}
