package Tests;

import Socket.RequestService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Seeder {
    public static void main(String[] args) {
        try {
            //instancia request
            RequestService req = new RequestService();

            //cria uma instancia de departamento
            String insertDepartamento = "departamento;Insert;departamentadasso;caixas;15";
            req.setRequest(insertDepartamento);
            req.execute();
            req.resetRequestService();

            //Cria alguns instancias de funcionario
            String insertFuncionarioStart = "funcionario;Insert;1111111111";
            String insertFuncionarioEnd = ";Antônio;ruaruaruaaaa;asgfsaf;0;1;";
            for (int c = 1; c < 5; c++) {
                String insertFuncionario = insertFuncionarioStart + c + insertFuncionarioEnd;
                req.setRequest(insertFuncionario);
                req.execute();
                req.resetRequestService();
            }

            //Cria alguns instancias de transportador
            String insertTransportadorStart = "transportador;INSERT;1111111111";
            String insertTransportadorEnd = ";jonaslima;olimpia;479974815021;120;1;";
            for (int c = 5; c < 9; c++) {
                String insertTransportador = insertTransportadorStart + c + insertTransportadorEnd;
                req.setRequest(insertTransportador);
                req.execute();
                req.resetRequestService();
            }

        } catch (Exception ex) {
            Logger.getLogger(Seeder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
