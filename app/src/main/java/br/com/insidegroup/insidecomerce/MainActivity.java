package br.com.insidegroup.insidecomerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.insidegroup.insidecomerce.Controles.Conexao;
import br.com.insidegroup.insidecomerce.entidades.Sessao;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnEntrar = (Button) findViewById(R.id.buttonEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Connection con = Conexao.getConnection();
                Sessao sessao = Sessao.getInstance();
                if (con == null) {
                    Toast.makeText(MainActivity.this, "Verifique sua conexão.", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        EditText elogin = (EditText) findViewById(R.id.editTextLogin);
                        EditText esenha = (EditText) findViewById(R.id.editTextSenha);

                        String login = elogin.getText().toString();
                        String senha = esenha.getText().toString();



                        Statement stmt = con.createStatement();
                        ResultSet rs = null;
                        String sql = "";

                        sql = "select * from usuarios where codusuario = '"+login +"' and senha = '"+senha+"'";
                        rs = stmt.executeQuery(sql);
                        if(rs.next()){
                            sessao.setCodUsuario(login);
                            sessao.setNomeUsuario(rs.getString("NOMEUSUARIO"));
                            Intent it = new Intent(MainActivity.this, ActListaTicket.class);
                            startActivity(it);
                            con.close();
                            elogin.setText("");
                            esenha.setText("");
                        }else{
                            Toast.makeText(MainActivity.this, "Usuário não identificado.", Toast.LENGTH_SHORT).show();
                            con.close();
                        }


                    } catch (SQLException e) {
                        e.printStackTrace();
                        try {
                            con.close();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }

                }




            }
        });


        //Util.showNotification(MainActivity.this, "teste");


        //inicia o service
        //Context c = getApplicationContext();
        //Intent intent = new Intent(c, InsideService.class);
        //c.startService(intent);

    }
}
