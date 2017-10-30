package br.com.insidegroup.insidecomerce.DAO;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.insidegroup.insidecomerce.Controles.Conexao;
import br.com.insidegroup.insidecomerce.entidades.Ticket;

public class DAOEventos {



public static List<String> getListaEventos(String codUsuario) throws SQLException {


    final List<String> lstEventos = new ArrayList<>();

    Connection con = Conexao.getConnection();
    Statement stmt = null;
    ResultSet rs = null;
    String sql = "";

    stmt = con.createStatement();

        sql = " SELECT " +
                " convert(varchar(20), data , 103)data, convert(varchar(20), data , 108) hora," +
                " (RUA + ', ' + NUMERO + ' - ' + BAIRRO) endereco" +
                " FROM TICKET_DATA_AGENDAMENTO INNER JOIN TICKET" +
                "   ON TICKET_DATA_AGENDAMENTO.IDTICKET = TICKET.ID" +
                " WHERE VENDEDORVINCULADO = '"+ codUsuario +"'" +
                " ORDER BY DATA";
        rs = stmt.executeQuery(sql);

        while(rs.next()){
            String evt = rs.getString("data")+";"+rs.getString("hora") + ";" + rs.getString("endereco");
            lstEventos.add(evt);
        }

    return lstEventos;
}



}
