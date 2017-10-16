package br.com.insidegroup.insidecomerce.DAO;


import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.insidegroup.insidecomerce.ActListaTicket;
import br.com.insidegroup.insidecomerce.Controles.Conexao;
import br.com.insidegroup.insidecomerce.entidades.Sessao;
import br.com.insidegroup.insidecomerce.entidades.Ticket;

public class DAOTIcket {



public static List<Ticket> getListaTickets(String codUsuario) throws SQLException {


    final List<Ticket> lstTicket = new ArrayList<>();

    Connection con = Conexao.getConnection();
    Statement stmt = null;
    ResultSet rs = null;
    String sql = "";

    stmt = con.createStatement();

        sql = "SELECT * FROM TICKET WHERE VENDEDORVINCULADO = '"+ codUsuario +"'";
        rs = stmt.executeQuery(sql);

        while(rs.next()){
            Ticket ticket = new Ticket();
            ticket.setStatus(rs.getInt("status"));
            ticket.setRua(rs.getString("rua"));
            ticket.setNumero(rs.getString("numero"));
            ticket.setBairro(rs.getString("bairro"));
            ticket.setCidade(rs.getString("cidade"));
            ticket.setEstado(rs.getString("estado"));
            ticket.setDataCriacao(rs.getString("datacriacao"));
            ticket.setNomeContato(rs.getString("nomeContato"));
            ticket.setTelefoneContato(rs.getString("telefoneContato"));
            ticket.setIdTicket(rs.getInt("id"));
            lstTicket.add(ticket);

        }

    return lstTicket;
}



}
