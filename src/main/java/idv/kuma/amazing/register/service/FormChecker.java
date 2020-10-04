package idv.kuma.amazing.register.service;

public interface FormChecker {
    void check(RegisterData registerData) throws CheckerException;
}
