package idv.kuma.amazing.register.controller;

import idv.kuma.amazing.register.service.*;
import org.springframework.stereotype.Component;

@Component
public class RegisterServiceFactory {

    public RegisterService create(Type type) throws RegisterServiceFactoryException {

        if (type.equals(Type.EMAIL)) {
            return new RegisterService(
                    new DataChecker(),
                    new EmailRegisterer(),
                    new TokenGenerator(),
                    new EmailMessenger()
            );
        } else if (type.equals(Type.PHONE)) {
            return new RegisterService(
                    new DataChecker(),
                    new PhoneRegisterer(),
                    new TokenGenerator(),
                    new PhoneMessenger()
            );
        }

        throw new RegisterServiceFactoryException("type unsupported");
    }
}
