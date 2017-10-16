package br.com.insidegroup.insidecomerce.DAO;


import android.content.Intent;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.insidegroup.insidecomerce.ActListaTicket;
import br.com.insidegroup.insidecomerce.Controles.Conexao;
import br.com.insidegroup.insidecomerce.MainActivity;
import br.com.insidegroup.insidecomerce.entidades.Sessao;

public class DAOSessao {





    public static Sessao loginSeguro(String login, String senha) throws SQLException {
        Connection con = Conexao.getConnection();


        Statement stmt = con.createStatement();
        ResultSet rs = null;
        String sql = "";

        sql = "select * from usuarios where codusuario = '"+login +"' and senha = '"+senha+"'";
        rs = stmt.executeQuery(sql);
        Sessao sessao =null;

        if(rs.next()){
            sessao = Sessao.getInstance();
            sessao.setCodUsuario(login);
            sessao.setNomeUsuario(rs.getString("NOMEUSUARIO"));

        }

        con.close();
        return sessao;


    }



}
