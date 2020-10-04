package idv.kuma.amazing.register.service;

public class RegisterService {

    private DataChecker checker;
    private Registerer registerer;
    private TokenGenerator tokenGenerator;
    private Messenger messenger;


    public RegisterService(DataChecker checker, Registerer registerer, TokenGenerator tokenGenerator, Messenger messenger) {
        this.checker = checker;
        this.registerer = registerer;
        this.tokenGenerator = tokenGenerator;
        this.messenger = messenger;
    }


    public String register(RegisterData registerData) throws ServiceException {

        try {
            checker.check(registerData);

            registerer.doRegister(registerData);

            messenger.send(registerData);

            return tokenGenerator.generate();

        } catch (CheckerException | DuplicatedRecordException | MessengerException e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }


    public DataChecker getChecker() {
        return checker;
    }


    public Registerer getRegisterer() {
        return registerer;
    }


    public TokenGenerator getTokenGenerator() {
        return tokenGenerator;
    }


    public Messenger getMessenger() {
        return messenger;
    }
}
