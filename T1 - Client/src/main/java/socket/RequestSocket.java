package Socket;

public class RequestSocket {
    private boolean status = true;
    private String request = "EMPTY";
    private String response = "START";
    
    public boolean isActive() {
        return status;
    }

    public void setActive(boolean status) {
        this.status = status;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
