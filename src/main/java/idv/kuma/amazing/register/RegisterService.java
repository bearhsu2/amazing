package idv.kuma.amazing.register;

import idv.kuma.amazing.RegisterException;
import org.springframework.stereotype.Component;

public class RegisterService {

    private Registerer registerer;
    private FormChecker checker;
    private Messenger messenger;
    private TokenGenerator tokenGenerator;


    public RegisterService(Registerer registerer, FormChecker checker, Messenger messenger, TokenGenerator tokenGenerator) {
        this.registerer = registerer;
        this.checker = checker;
        this.messenger = messenger;
        this.tokenGenerator = tokenGenerator;
    }


    SuccessResponse register(RegisterForm registerForm) throws RegisterException {

        try {
            checker.check(registerForm);

            registerer.doRegister(registerForm);

            messenger.send();

            String token = tokenGenerator.generate();

            return new SuccessResponse(token);
        } catch (Exception e) {
            throw new RegisterException(e.getMessage());
        }

    }
}
