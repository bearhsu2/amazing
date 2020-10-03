package idv.kuma.amazing.register;

import idv.kuma.amazing.RegisterException;
import org.springframework.stereotype.Component;

@Component
public class RegisterService {

    public SuccessResponse register(RegisterForm registerForm) throws RegisterException {
        return new SuccessResponse("aaaaabbbbb");
    }
}
