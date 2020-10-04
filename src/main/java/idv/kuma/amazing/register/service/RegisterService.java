package idv.kuma.amazing.register.service;

import idv.kuma.amazing.RegisterException;
import idv.kuma.amazing.register.controller.RegisterForm;

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


    public String register(RegisterForm registerForm) throws RegisterException {

        try {
            checker.check(registerForm);

            registerer.doRegister(registerForm);

            messenger.send();

            return tokenGenerator.generate();

        } catch (CheckerException e) {
            throw new RegisterException(e.getMessage());
        }

    }
}
