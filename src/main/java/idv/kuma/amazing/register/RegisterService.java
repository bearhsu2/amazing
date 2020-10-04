package idv.kuma.amazing.register;

import idv.kuma.amazing.RegisterException;

public class RegisterService {

    private FormChecker checker;
    private Registerer registerer;
    private TokenGenerator tokenGenerator;
    private Messenger messenger;


    public RegisterService(FormChecker checker, Registerer registerer, TokenGenerator tokenGenerator, Messenger messenger) {
        this.checker = checker;
        this.registerer = registerer;
        this.tokenGenerator = tokenGenerator;
        this.messenger = messenger;
    }


    String register(RegisterForm registerForm) throws RegisterException {

        try {
            checker.check(registerForm);

            registerer.doRegister(registerForm);

            messenger.send();

            return tokenGenerator.generate();

        } catch (Exception e) {
            throw new RegisterException(e.getMessage());
        }

    }
}
