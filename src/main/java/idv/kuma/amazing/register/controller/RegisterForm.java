package idv.kuma.amazing.register.controller;

import idv.kuma.amazing.register.service.RegisterData;

public class RegisterForm {

    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    private Type type;


    public RegisterForm(String name, String email, String password, String confirmPassword, Type type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.type = type;
    }


    public Type getType() {
        return type;
    }


    public void setType(Type type) {
        this.type = type;
    }


    public String getConfirmPassword() {
        return confirmPassword;
    }


    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


    public RegisterData toRegisterData() {
        return new RegisterData(name, email, password, confirmPassword);
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }
}
