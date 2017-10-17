package br.com.insidegroup.insidecomerce;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.insidegroup.insidecomerce.Controles.Conexao;
import br.com.insidegroup.insidecomerce.entidades.Sessao;

public class TicketHistorico extends AppCompatActivity {

    private static Sessao sessao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_historico);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.sessao = Sessao.getInstance();

        exibeHistorico();

        Button buttonVoltar = (Button) findViewById(R.id.buttonHistoricoOk);
        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });



    }


    public void exibeHistorico(){

        Connection con = Conexao.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "";



        sql = "select convert(varchar(10), datacriacao, 103) + ' - ' + descricao descricao, descricao2 from ticket_historico where idTicket = "+ sessao.getTicket().getIdTicket();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            String texto = "";
            while(rs.next()){

                String descricao = rs.getString("descricao");
                String motivo = rs.getString("descricao2");

                texto +=  descricao + "/n";
                if(motivo != null)
                    texto += "     Motivo: " +motivo +"/n/n";

                texto += "/n";

            }

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
