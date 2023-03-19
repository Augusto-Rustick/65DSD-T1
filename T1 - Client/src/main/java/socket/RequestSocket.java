package socket;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RequestSocket {

    protected boolean status = true;
    protected String request = "EMPTY";
    protected String response = "START";

    protected String[] arrayDados = new String[8];
    protected String[] jsonKeys;
    protected String baseUrl = "http://localhost:8080/";

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
        String formatedUrl = baseUrl + arrayDados[0].toLowerCase() + "/" + arrayDados[1].toLowerCase();
        String responseBody = "";
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            String methodName = "request" + arrayDados[1];
            Method method = this.getClass().getMethod(methodName, CloseableHttpClient.class, String.class);
            responseBody = (String) method.invoke(this, httpClient, formatedUrl);
        } catch (Exception ex) {
            return ex.getMessage();
        } finally {
            httpClient.close();
        }
        return responseBody;
    }

    public String requestGet(CloseableHttpClient httpClient, String formatedUrl) throws IOException {
        formatedUrl += "/" + arrayDados[7];
        HttpGet request = new HttpGet(formatedUrl);
        var response = httpClient.execute(request);
        return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
    }

    public String requestList(CloseableHttpClient httpClient, String formatedUrl) throws IOException {
        HttpGet request = new HttpGet(formatedUrl);
        var response = httpClient.execute(request);
        return EntityUtils.toString(response.getEntity());
    }

    public String requestRemove(CloseableHttpClient httpClient, String formatedUrl) throws IOException {
        formatedUrl += "/" + arrayDados[7];
        HttpDelete request = new HttpDelete(formatedUrl);
        var response = httpClient.execute(request);
        return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
    }

    public String requestInsert(CloseableHttpClient httpClient, String formatedUrl) throws IOException {
        HttpPost request = new HttpPost(formatedUrl);
        JSONObject json = this.getJson(false);
        StringEntity params = new StringEntity(json.toString());
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        var response = httpClient.execute(request);
        return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
    }

    protected JSONObject getJson(boolean isUpdate) {
        JSONObject json = new JSONObject();

        int forInteration = arrayDados[0].equalsIgnoreCase("departamento") ? 5 : 7;
        
        if(isUpdate){
            forInteration++;
        }
        
        for (int c = 2; c < forInteration; c++) {
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
