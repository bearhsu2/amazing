package idv.kuma.amazing.register.service;

public interface DataChecker {
    void check(RegisterData registerData) throws CheckerException;
}
