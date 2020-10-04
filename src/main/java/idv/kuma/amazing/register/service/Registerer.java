package idv.kuma.amazing.register.service;

import idv.kuma.amazing.register.controller.RegisterForm;

public interface Registerer {
    void doRegister(RegisterForm registerForm) throws RegisterException;
}
