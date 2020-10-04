package idv.kuma.amazing.register.controller;

import idv.kuma.amazing.register.controller.Response;

public class SuccessResponse extends Response {
    private String token;


    public SuccessResponse(String token) {
        this.token = token;
    }


    public String getToken() {
        return token;
    }


    public void setToken(String token) {
        this.token = token;
    }


}
