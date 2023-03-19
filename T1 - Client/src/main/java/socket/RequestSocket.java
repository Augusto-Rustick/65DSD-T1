package socket;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

public class RequestSocket {

    protected boolean status = true;
    protected String request = "EMPTY";
    protected String response = "START";

    protected String[] arrayDados = new String[7];
    protected String[] jsonKeys;

    public RequestSocket() {
    }
    
    public RequestSocket(String request) {
        arrayDados = request.split(";");
    }

    public String execute() {
        try {
            return this.requesting();
        } catch (Exception ex) {
            Logger.getLogger(RequestSocket.class.getName()).log(Level.SEVERE, null, ex);
            return "Erro ao tentar fazer a consulta";
        }
    }

    protected String requesting() throws Exception {
        JSONObject json = this.getJson();
        String sUrl = "http://localhost:8080/cliente/save";

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {
            HttpPost request = new HttpPost(sUrl);
            StringEntity params = new StringEntity(json.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            httpClient.execute(request);
        } catch (Exception ex) {
            // handle exception here
        } finally {
            httpClient.close();
        }
        return json.toString();
    }

    protected JSONObject getJson() {

        JSONObject json = new JSONObject();
        for (int c = 2; c < 7; c++) {
            json.put(jsonKeys[c], arrayDados[c]);
        }

        return json;
    }

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
