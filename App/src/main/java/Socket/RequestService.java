package Socket;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RequestService {

    private String[] arrayDados;
    private String[] jsonKeys;
    private String baseUrl = "http://localhost:8080/";
    private String request;

    public String execute() {
        try {
            return this.requesting();
        } catch (Exception ex) {
            if (arrayDados[0] != null) {
                return "Erro ao tentar fazer a requisição de tipo " + arrayDados[0];
            }
            return "Erro ao informar o tipo de requisição";
        }
    }

    private String requesting() {
        String responseBody = "";
        String formatedUrl = baseUrl + arrayDados[0].toLowerCase() + "/" + arrayDados[1].toLowerCase();
        if (arrayDados.length > 2 && arrayDados[2] != null && !arrayDados[2].equalsIgnoreCase("")) {
            formatedUrl += "/" + arrayDados[2];
        }
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            String methodName = "request" + arrayDados[1].toUpperCase();
            Method method = this.getClass().getMethod(methodName, CloseableHttpClient.class, String.class);
            responseBody = (String) method.invoke(this, httpClient, formatedUrl);
        } catch (Exception ex) {
            return "Erro ao tentar fazer a requisição de tipo " + arrayDados[1];
        }
        if (responseBody.isEmpty()) {
            return "A operração de " + arrayDados[1] + " foi um sucesso!";
        }
        return responseBody;
    }

    public String requestGET(CloseableHttpClient httpClient, String formatedUrl) throws IOException {
        HttpGet request = new HttpGet(formatedUrl);
        return this.requestGETorLISTorDELETE(httpClient, request);
    }

    public String requestLIST(CloseableHttpClient httpClient, String formatedUrl) throws IOException {
        HttpGet request = new HttpGet(formatedUrl);
        return this.requestGETorLISTorDELETE(httpClient, request);
    }

    public String requestDELETE(CloseableHttpClient httpClient, String formatedUrl) throws IOException {
        HttpDelete request = new HttpDelete(formatedUrl);
        return this.requestGETorLISTorDELETE(httpClient, request);
    }

    private String requestGETorLISTorDELETE(CloseableHttpClient httpClient, HttpRequestBase request) throws IOException {
        var response = httpClient.execute(request);
        if (response.getEntity() != null) {
            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        }
        return response.getStatusLine().toString();
    }

    public String requestINSERT(CloseableHttpClient httpClient, String formatedUrl) throws IOException {
        return this.requestINSERTorUPDATE(httpClient, false);
    }

    public String requestUPDATE(CloseableHttpClient httpClient, String formatedUrl) throws IOException {
        return this.requestINSERTorUPDATE(httpClient, true);
    }

    private String requestINSERTorUPDATE(CloseableHttpClient httpClient, boolean isUpdate) throws IOException {
        String formatedUrl = baseUrl + arrayDados[0].toLowerCase() + "/insertOrUpdate";
        HttpPost request = new HttpPost(formatedUrl);
        JSONObject json = this.getJson(isUpdate);
        StringEntity params = new StringEntity(json.toString());
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        var response = httpClient.execute(request);
        if (response.getEntity() != null) {
            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        }
        return response.getStatusLine().toString();
    }

    private JSONObject getJson(boolean isUpdate) {
        JSONObject json = new JSONObject();
        this.renderJsonKeys();
        int forInteration = this.getJsonForInteration(isUpdate);
        for (int c = 2; c < forInteration; c++) {
            json.put(jsonKeys[c], arrayDados[c]);
        }
        System.out.println(Arrays.toString(arrayDados));
        System.out.println(json);
        return json;
    }

    private void renderJsonKeys() {
        switch (arrayDados[0]) {
            case "transportador" ->
                    jsonKeys = new String[]{"entity", "requestType", "cpf", "nome", "endereco", "telefone", "carregamento", "departamento", "id"};
            case "funcionario" ->
                    jsonKeys = new String[]{"entity", "requestType", "cpf", "nome", "endereco", "ctps", "quantidadeVendas", "departamento", "id"};
            case "departamento" ->
                    jsonKeys = new String[]{"entity", "requestType", "nome", "produto", "quantidadeEstoque", "id"};
        }
    }


    private int getJsonForInteration(boolean isUpdate) {
        int forInteration = arrayDados[0].equalsIgnoreCase("departamento") ? 5 : 8;
        if (isUpdate) {
            forInteration++;
        }
        return forInteration;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) throws Exception {
        this.request = request;
        this.splitRequest();
    }

    private void splitRequest() throws Exception {
        arrayDados = this.getRequest().split(";");
    }

    public void resetRequestService() {
        this.request = "";
        arrayDados = new String[8];
    }
}
