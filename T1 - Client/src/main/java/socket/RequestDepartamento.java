/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socket;

/**
 *
 * @author arust
 */
public class RequestDepartamento extends RequestSocket{
    
    public RequestDepartamento(String request){
        super(request);
        jsonKeys = new String[]{"entity", "requestType", "nome", "produto", "quantidade"};
    }
}
