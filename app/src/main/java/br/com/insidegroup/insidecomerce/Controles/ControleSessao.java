package br.com.insidegroup.insidecomerce.Controles;


import android.util.Log;

import java.sql.SQLException;
import br.com.insidegroup.insidecomerce.util.Erro;

import br.com.insidegroup.insidecomerce.DAO.DAOSessao;
import br.com.insidegroup.insidecomerce.entidades.Sessao;
import br.com.insidegroup.insidecomerce.util.EnumMensagemErro;

public class ControleSessao {





    public static Sessao loginSeguro(String login, String senha) throws Erro{

        try {

            Sessao s = DAOSessao.loginSeguro(login, senha);




            if (s == null){
                throw new Erro(EnumMensagemErro.MENSAGEM_ERRO_USUARIO_NAO_INDENTIFICADO.getMsg());
            }


            return s;
        } catch (SQLException e) {
            Log.i("Info","InsideComerce "+e.getMessage());
            throw new Erro(EnumMensagemErro.MENSAGEM_ERRO_AO_IDENTIFICAR_USUARIO.getMsg());

        }



    }




}
