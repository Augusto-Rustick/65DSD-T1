import Socket.Client;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.Scanner;

public class TextClient {

    protected Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    @Parameter(names = {"--host", "-h"})
    protected String host;
    @Parameter(names = {"--port", "-p"})
    protected int port;
    @Parameter(names = {"--timeout", "-t"})
    protected int timeout;

    public static void main(String... argv) throws IOException {
        TextClient main = new TextClient();
        JCommander.newBuilder().addObject(main).build().parse(argv);
        main.run();
    }

    public String returnTips(String string) {
        String[] strings = string.split("/");
        String tip = "";
        if (strings.length <= 1)
            return "";
        switch (strings[0].toUpperCase()) {
            case "DEPARTAMENTO" -> {
                switch (strings[1].toUpperCase()) {
                    case "GET" -> {
                        tip = "departamento;GET;nome";
                    }
                    case "INSERT" -> {
                        tip = "departamento;INSERT;nome;produto;qntProduto;";
                    }
                    case "LIST" -> {
                        tip = "departamento;LIST";
                    }
                    case "DELETE" -> {
                        tip = "departamento;DELETE;nome";
                    }
                    case "UPDATE" -> {
                        tip = "departamento;INSERT;nome;produto;qntProduto;id";
                    }
                    case "PESSOAS" -> {
                        tip = "pessoa;LIST;idDepartamento;";
                    }
                }
            }
            case "FUNCIONARIO" -> {
                switch (strings[1].toUpperCase()) {
                    case "GET" -> {
                        tip = "funcionario;GET;cpf";
                    }
                    case "INSERT" -> {
                        tip = "funcionario;INSERT;cpf;nome;endereco;ctps;numVendas;idDepartamento;";
                    }
                    case "LIST" -> {
                        tip = "funcionario;LIST";
                    }
                    case "DELETE" -> {
                        tip = "funcionario;DELETE;cpf";
                    }
                    case "UPDATE" -> {
                        tip = "funcionario;INSERT;cpf;nome;endereco;ctps;numVendas;idDepartamento;id;";
                    }
                }
            }
            case "TRANSPORTADOR" -> {
                switch (strings[1].toUpperCase()) {
                    case "GET" -> {
                        tip = "transportador;GET;cpf";
                    }
                    case "INSERT" -> {
                        tip = "transportador;INSERT;cpf;nome;endereco;telefone;numEstoque;idDepartamento;";
                    }
                    case "LIST" -> {
                        tip = "transportador;LIST";
                    }
                    case "DELETE" -> {
                        tip = "transportador;DELETE;cpf";
                    }
                    case "UPDATE" -> {
                        tip = "transportador;INSERT;cpf;nome;endereco;telefone;numEstoque;idDepartamento;id;";
                    }
                }
            }
        }
        if (!tip.isEmpty()) {
            clipboard.setContents(new StringSelection(tip), null);
            tip += ".. O conteúdo copiado para a aréa de transferência!";
        }
        return tip;
    }

    private void run() throws IOException {
        Client client = new Client(host, port, timeout);
        Scanner scanner = new Scanner(System.in);

        System.out.println(
                """
                        Entidades:\s

                           - departamento: GET/INSERT/LIST/DELETE/UPDATE/PESSOAS
                           - funcionario: GET/INSERT/LIST/DELETE/UPDATE
                           - transportador: GET/INSERT/LIST/DELETE/UPDATE

                           Dica: A qualquer momento digite entidade/metodo para obter informações de uso!
                           
                           Informação: Para inserir espaços coloque um '%'!
                           
                           """
        );


        while (true) {
            String cliInput = scanner.next();
            String tips = returnTips(cliInput);
            if (cliInput.equals(("exit")))
                break;
            if (tips.equals(""))
                System.out.println(client.write(cliInput.replace('%', ' ')));
            else
                System.out.println(tips);
        }

    }
}
