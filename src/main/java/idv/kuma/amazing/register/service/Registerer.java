package idv.kuma.amazing.register.service;

public interface Registerer {
    void doRegister(RegisterData registerData) throws RegisterException;
}
