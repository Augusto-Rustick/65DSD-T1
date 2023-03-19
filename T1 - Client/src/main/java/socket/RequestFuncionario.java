/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socket;

/**
 *
 * @author arust
 */
public class RequestFuncionario extends RequestSocket{
    
    public RequestFuncionario(String request){
        super(request);
        jsonKeys = new String[]{"entity", "requestType", "cpf", "nome", "rua", "ctps", "quantidadeVendas"};
    }
}
