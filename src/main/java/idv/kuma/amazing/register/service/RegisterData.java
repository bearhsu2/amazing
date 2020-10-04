package idv.kuma.amazing.register.service;

import java.util.Objects;

public class RegisterData {


    private String name;
    private String email;
    private String password;


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


    public RegisterData(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterData data = (RegisterData) o;
        return Objects.equals(name, data.name) &&
                Objects.equals(email, data.email) &&
                Objects.equals(password, data.password);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, email, password);
    }
}
