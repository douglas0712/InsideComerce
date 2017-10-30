package br.com.insidegroup.insidecomerce.entidades;

import java.sql.Connection;
import java.sql.Statement;

import br.com.insidegroup.insidecomerce.Controles.Conexao;
import br.com.insidegroup.insidecomerce.util.Util;

/**
 * Created by Lucas on 17/10/2017.
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsereDataAgendamento {


    public void insereDataAgendamento(Ticket ticket, String data, String hora) {
        Connection con = Conexao.getConnection();
        try {
            Statement stmt = con.createStatement();

            String sql ="INSERT INTO TICKET_DATA_AGENDAMENTO (IDTICKET, DATA) VALUES ("+ticket.getIdTicket() + ",'"+ Util.convertDataParaFormatoBanco(data) + "' '"+hora+"')";
            stmt.executeUpdate(sql);

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
