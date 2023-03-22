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
        testeInsert = "transportador;INSERT;12312312311;jonaslima;olimpia;479974815021;120;1;";
//                       transportador;INSERT;5218948484848484;dueh8ydgeydg;olialsbnwuyhbsgy;5298475417717;120;1;
        testeUpdate = "transportador;Update;1561616515165165165;Augustooooo;ruaruaruaaaa;48984827115;25;2;103";
        testeGet = "transportador;Get;1561616515165165165";
        testeList = "transportador;List";
        testeRemove = "transportador;Delete;2";
//
        testeInsert = "funcionario;Insert;123423131541235;Augusto;ruaruaruaaaa;asgfsaf;0;15;";
//                       funcionario;INSERT;968745987484579;uyhgdsuyhsda;ydeswayeduh;51894;52;
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
            req = new RequestService();
            req.setRequest(testeInsert);
            String retorno = req.execute();
            System.out.println(retorno);
        } catch (Exception ex) {
            Logger.getLogger(executeRequest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
