package Components;

import Socket.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioComboBox extends JComboBox<Funcionario> {

    public FuncionarioComboBox(Client c) throws IOException {
        super();
        fillDepComboBox(c);
    }

    private void fillDepComboBox(Client client) throws IOException {
        String response = "{ data: "+client.write("Funcionario;LIST;")+"}";
        JSONObject json = new JSONObject(response);
        ObjectMapper objectMapper = new ObjectMapper();
        Funcionario[] list = (Funcionario[]) objectMapper.readValue(json.get("data").toString(), Funcionario[].class);
        ArrayList<Funcionario> deps = new ArrayList<>(List.of(list));
        for (Funcionario dep : deps) {
            this.addItem(dep);
        }
    }

    public int getId(){
        Funcionario d = (Funcionario) this.getSelectedItem();
        assert d != null;
        return d.getId();
    }

}
