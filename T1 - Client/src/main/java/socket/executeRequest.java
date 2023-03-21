/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Socket;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arust
 */
public class executeRequest {

    public static void main(String[] args) {
        String testeInsert = "";
        String testeUpdate = "";
        String testeGet = "";
        String testeList = "";
        String testeRemove = "";
//
        testeInsert = "cliente;Insert;15616165165165165;Augusto;ruaruaruaaaa;48984827115;teste@gmail.com;";
        testeUpdate = "cliente;Update;61616464684642;Augustooooo;ruaruaruaaaa;48984827115;teste@gmail.com;752";
        testeGet = "cliente;Get;12545625435";
        testeList = "cliente;List";
        testeRemove = "cliente;Delete;752";
//
//        testeInsert = "funcionario;Insert;123423131541235;Augusto;ruaruaruaaaa;asgfsaf;15;";
//        testeUpdate = "funcionario;Update;123423131541235;Augustooooooo;ruaruaruaaaa;asgfsaf;15;807";
//        testeGet = "funcionario;Get;123423131541235";
//        testeList = "funcionario;List";
//        testeRemove = "funcionario;Delete;853";
//
//        testeInsert = "departamento;Insert;departamentadasso;almas;15";
//        testeUpdate = "departamento;Update;departgyjfhsaamentadassozadoasdo;almas;15;303";
//        testeGet = "departamento;Get;departgyjfhsaamentadassozadoasdo";
//        testeList = "departamento;List";
//        testeRemove = "departamento;Delete;254";

        RequestService req;
        try {
            req = new RequestService(testeInsert);
            String retorno = req.execute();
            System.out.println(retorno);
        } catch (Exception ex) {
            Logger.getLogger(executeRequest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
