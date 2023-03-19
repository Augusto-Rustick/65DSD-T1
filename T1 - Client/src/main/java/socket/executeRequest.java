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
//        String testeInsert = "cliente;Insert;12545625435;Augusto;ruaruaruaaaa;48984827115;teste@gmail.com;";
//        String testeGet = "cliente;Get;;;;;;404";
//        String testeList = "cliente;List;;;;;;";
//        String testeRemove = "cliente;Remove;;;;;;402";
//        String testeUpdate = "cliente;Insert;12545625435;Augusto;ruaruarua;48984827115;teste@gmail.com;402";

//        String testeInsert = "funcionario;Insert;12545625435;Augusto;ruaruaruaaaa;asgfsaf;15;";
//        String testeGet = "funcionario;Get;;;;;;452";
//        String testeList = "funcionario;List;;;;;;";
//        String testeRemove = "funcionario;Remove;;;;;;402";
//        String testeUpdate = "funcionario;Insert;12545625435;Augusto;ruaruarua;asgfsaf;25;402";
//
        String testeInsert = "departamento;Insert;departamentado;almas;15;";
        String testeGet = "departamento;Get;;;;;;2";
        String testeList = "departamento;List;;;;;;";
        String testeRemove = "departamento;Remove;;;;;;54";
        String testeUpdate = "departamento;Insert;departamentado;almas;15;";

        RequestSocket req = new RequestSocket(testeList);
        String retorno = req.execute();
        System.out.println(retorno);
    }
}
