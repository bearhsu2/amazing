package idv.kuma.amazing.register.service;

import idv.kuma.amazing.register.controller.Type;
import org.springframework.stereotype.Component;

@Component
public class RegisterServiceFactory {

    public RegisterService create(Type type) {

        if (type.equals(Type.EMAIL)) {
            return new RegisterService(
                    new EmailDataChecker(),
                    new EmailRegisterer(),
                    new TokenGenerator(),
                    new EmailMessenger()
            );
        } else {
            return new RegisterService(
                    null,
                    null,
                    new TokenGenerator(),
                    null
            );
        }
    }
}
