package idv.kuma.amazing.register.service;

import org.springframework.util.StringUtils;

public class EmailDataChecker implements DataChecker {
    @Override
    public void check(RegisterData registerData) throws CheckerException {

        String password = registerData.getPassword();

        if (StringUtils.isEmpty(password) || password.length() < 8) {
            throw new CheckerException("Password too short.");
        }

        if (!registerData.getPassword().equals(registerData.getConfirmPassword())) {
            throw new CheckerException("Re-enter Password incorrect.");
        }

    }
}
