package idv.kuma.amazing;

public class Response {

    public Response(String token) {
        this.token = token;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public String getToken() {
        return token;
    }


    public void setToken(String token) {
        this.token = token;
    }


    private String token;
    private String message;
}
