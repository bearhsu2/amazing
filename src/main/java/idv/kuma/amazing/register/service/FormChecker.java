package idv.kuma.amazing.register.service;

import idv.kuma.amazing.register.controller.RegisterForm;

public interface FormChecker {
    void check(RegisterForm registerForm) throws CheckerException;
}
