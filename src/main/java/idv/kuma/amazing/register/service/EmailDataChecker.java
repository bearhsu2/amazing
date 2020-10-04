package idv.kuma.amazing.register.service;

public class EmailDataChecker implements DataChecker{
    @Override
    public void check(RegisterData registerData) throws CheckerException {

        String password = registerData.getPassword();

        if (password.length() < 8) {
            throw new CheckerException("Password too short.");
        }

    }
}
