package idv.kuma.amazing.register.controller;

import idv.kuma.amazing.register.service.RegisterData;

public class RegisterForm {

    private String name;
    private String email;
    private String password;


    public RegisterForm(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }


    public RegisterData toRegisterData() {
        return new RegisterData(name, email, password);
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
