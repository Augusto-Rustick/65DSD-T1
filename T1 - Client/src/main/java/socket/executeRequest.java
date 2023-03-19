/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socket;

/**
 *
 * @author arust
 */
public class executeRequest {
    public static void main(String[] args) {
        String testeInsert = "cliente;Insert;12545625435;Augusto;ruaruarua;48984827115;teste@gmail.com;";
        String testeGet = "cliente;Get;;;;;;202";
        String testeList = "cliente;List;;;;;;";
        String testeRemove = "cliente;Remove;;;;;;269";
        String testeUpdate = "cliente;Insert;12545625435;Augusto;ruaruarua;48984827115;teste@gmail.com;267";
        RequestSocket req = new RequestCliente(testeList);
        String retorno = req.execute();
        System.out.println(retorno);
    }
}
