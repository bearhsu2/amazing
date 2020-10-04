package idv.kuma.amazing.register;

import idv.kuma.amazing.register.service.RegisterService;
import org.springframework.stereotype.Component;

@Component
public class RegisterServiceFactory {

    public RegisterService create(RegisterForm registerForm) {
        return null;
    }
}
