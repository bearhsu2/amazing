package idv.kuma.amazing.register.service;

import org.springframework.util.StringUtils;

public class DataChecker {


    public void check(RegisterData registerData) throws CheckerException {

        String password = registerData.getPassword();

        if (StringUtils.isEmpty(password) || password.length() < 8) {
            throw new CheckerException("password.too.short");
        }

        if (!registerData.getPassword().equals(registerData.getConfirmPassword())) {
            throw new CheckerException("reenter.password.incorrect");
        }

    }
}
