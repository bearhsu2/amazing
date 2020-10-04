package idv.kuma.amazing.register.service;

import idv.kuma.amazing.ServiceException;
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


    public String register(RegisterForm registerForm) throws ServiceException {

        try {
            checker.check(registerForm);

            registerer.doRegister(registerForm);

            messenger.send();

            return tokenGenerator.generate();

        } catch (CheckerException | RegisterException e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }
}
