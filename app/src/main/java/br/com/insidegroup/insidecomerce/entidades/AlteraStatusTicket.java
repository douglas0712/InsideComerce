package br.com.insidegroup.insidecomerce.entidades;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import br.com.insidegroup.insidecomerce.Controles.Conexao;

/**
 * Created by Lucas on 09/10/2017.
 */

public class AlteraStatusTicket {

    public static boolean AlteraStatusTicket(Ticket ticket, int novoStatus, String descicaoAuxiliar){

        Connection con = Conexao.getConnection();
        try {
            Statement stmt = con.createStatement();

            String sql = "UPDATE TICKET SET STATUS = '"+novoStatus+"' WHERE id = "+ticket.getIdTicket();
            sql += " INSERT INTO TICKET_HISTORICO (IDTICKET, DATACRIACAO, DESCRICAO, DESCRICAO2) VALUES ("+ticket.getIdTicket()+
                        ", getdate(), 'Ticket Alterado o Status de: "+DescricaoStatuTicket.descricaoStatusTicket(ticket.getStatus())
                                                                     +"; para: "+DescricaoStatuTicket.descricaoStatusTicket(novoStatus)+"', '"+descicaoAuxiliar+"')";
            stmt.executeUpdate(sql);

            con.close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }


}
