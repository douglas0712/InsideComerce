package br.com.insidegroup.insidecomerce.Controles;


import android.util.Log;

import java.sql.SQLException;
import java.util.List;

import br.com.insidegroup.insidecomerce.DAO.DAOTIcket;
import br.com.insidegroup.insidecomerce.entidades.Ticket;
import br.com.insidegroup.insidecomerce.util.EnumMensagemErro;
import br.com.insidegroup.insidecomerce.util.Erro;

public class ControleTicket {



    public static List<Ticket> getListaTicket(String codUsuario) throws Erro {


        try {
            List<Ticket> lstTicket = DAOTIcket.getListaTickets(codUsuario);
            return lstTicket;
        } catch (SQLException e) {
            Log.i("Info", "InsideComerce"+ e.getMessage());
            throw  new Erro(EnumMensagemErro.MENSAGEM_ERRO_OBTER_LISTA_TICKET.getMsg());

        }
    }

}
