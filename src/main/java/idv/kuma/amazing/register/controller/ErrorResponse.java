package idv.kuma.amazing.register.controller;

public class ErrorResponse extends Response {

    private String errorMessage;


    public ErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public String getErrorMessage() {
        return errorMessage;
    }


    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
