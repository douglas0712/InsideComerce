package br.com.insidegroup.insidecomerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.insidegroup.insidecomerce.Controles.Conexao;

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

                if (con == null) {
                    Toast.makeText(MainActivity.this, "Sem conexão", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        Statement stmt = con.createStatement();
                        ResultSet rs = null;
                        String sql = "";

                        sql = "select * from usuarios";
                        rs = stmt.executeQuery(sql);
                        rs.next();

                        String a = rs.getString("NOMEUSUARIO");

                        Toast.makeText(MainActivity.this, a, Toast.LENGTH_SHORT).show();



                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }


                Intent it = new Intent(MainActivity.this, ActListaTicket.class);
                startActivity(it);
            }
        });


        //Util.showNotification(MainActivity.this, "teste");


        //inicia o service
        //Context c = getApplicationContext();
        //Intent intent = new Intent(c, InsideService.class);
        //c.startService(intent);

    }
}
