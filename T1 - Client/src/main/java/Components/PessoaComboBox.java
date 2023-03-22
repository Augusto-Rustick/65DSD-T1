package Components;

import Socket.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PessoaComboBox extends JComboBox<Pessoa> {

    public PessoaComboBox(Client c, PessoaTipo p) throws IOException {
        super();
        fillDepComboBox(c, p);
        this.addActionListener(e->{
            try {
                fillDepComboBox(c, p);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void fillDepComboBox(Client client, PessoaTipo pessoa) throws IOException {
        String response = "{ data: "+client.write(pessoa+";LIST;")+"}";
        JSONObject json = new JSONObject(response);
        ObjectMapper objectMapper = new ObjectMapper();
        Pessoa[] list = (Pessoa[]) objectMapper.readValue(json.get("data").toString(), Pessoa[].class);
        ArrayList<Pessoa> deps = new ArrayList<>(List.of(list));
        this.removeAllItems();
        for (Pessoa dep : deps) {
            this.addItem(dep);
        }
    }

    public int getId(){
        Pessoa d = (Pessoa) this.getSelectedItem();
        assert d != null;
        return d.getId();
    }

    public int getDepId(){
        Pessoa d = (Pessoa) this.getSelectedItem();
        assert d != null;
        return Integer.parseInt(d.getDepartamento());
    }
}
