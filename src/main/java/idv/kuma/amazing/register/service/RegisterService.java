package idv.kuma.amazing.register.service;

import idv.kuma.amazing.ServiceException;

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


    public String register(RegisterData registerData) throws ServiceException {

        try {
            checker.check(registerData);

            registerer.doRegister(registerData);

            messenger.send();

            return tokenGenerator.generate();

        } catch (CheckerException | RegisterException e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }
}
