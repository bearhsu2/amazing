package idv.kuma.amazing.register;

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
