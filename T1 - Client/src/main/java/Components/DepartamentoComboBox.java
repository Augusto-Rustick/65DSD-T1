package Components;

import Socket.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoComboBox extends JComboBox<Departamento> {

    public DepartamentoComboBox(Client c) throws IOException {
        super();
        fillDepComboBox(c);
    }

    private void fillDepComboBox(Client client) throws IOException {
        String response = "{ data: "+client.write("departamento;LIST;")+"}";
        JSONObject json = new JSONObject(response);
        ObjectMapper objectMapper = new ObjectMapper();
        Departamento[] list = (Departamento[]) objectMapper.readValue(json.get("data").toString(), Departamento[].class);
        ArrayList<Departamento> deps = new ArrayList<>(List.of(list));
        this.removeAllItems();
        for (Departamento dep : deps) {
            this.addItem(dep);
        }
    }

    public int getId(){
        Departamento d = (Departamento) this.getSelectedItem();
        assert d != null;
        return d.getId();
    }

}
