package Components;

import Socket.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoComboBox extends JComboBox<Produto> {

    public ProdutoComboBox(Client c) throws IOException {
        super();
        fillDepComboBox(c);
    }

    private void fillDepComboBox(Client client) throws IOException {
        String response = "{ data: "+client.write("Departamento;LIST;")+"}";
        JSONObject json = new JSONObject(response);
        ObjectMapper objectMapper = new ObjectMapper();
        Produto[] list = (Produto[]) objectMapper.readValue(json.get("data").toString(), Produto[].class);
        ArrayList<Produto> deps = new ArrayList<>(List.of(list));
        for (Produto dep : deps) {
            this.addItem(dep);
        }
    }

    public int getId(){
        Produto d = (Produto) this.getSelectedItem();
        assert d != null;
        return d.getId();
    }

}
