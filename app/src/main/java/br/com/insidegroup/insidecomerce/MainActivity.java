package br.com.insidegroup.insidecomerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import br.com.insidegroup.insidecomerce.Controles.ControleSessao;
import br.com.insidegroup.insidecomerce.entidades.Sessao;
import br.com.insidegroup.insidecomerce.util.EnumMensagemErro;
import br.com.insidegroup.insidecomerce.util.Erro;
import br.com.insidegroup.insidecomerce.util.Util;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnEntrar = (Button) findViewById(R.id.buttonEntrar);

        //inicia o service
        //Context c = getApplicationContext();
        //Intent intent = new Intent(c, InsideService.class);
        //c.startService(intent);

    }


    public void onClickBtnEntrar(View v){

//            if (con == null) {
//                Toast.makeText(MainActivity.this, "Verifique sua conexão.", Toast.LENGTH_SHORT).show();
//            }

            try {

                    EditText elogin = (EditText) findViewById(R.id.editTextLogin);
                    EditText esenha = (EditText) findViewById(R.id.editTextSenha);

                    String login = elogin.getText().toString();
                    String senha = esenha.getText().toString();

                    ControleSessao.loginSeguro(login, senha);

                    elogin.setText("");
                    esenha.setText("");

                    Intent it = new Intent(MainActivity.this, ActListaTicket.class);
                    startActivity(it);

            } catch (Erro erro) {
                Log.i("info", "insideComerce "+erro.getMessage());
                Util.ExibirMensagemNaTela(MainActivity.this, erro.getMsg());
            } catch (Exception e) {
                Log.i("info", "insideComerce "+e.getMessage());
                Util.ExibirMensagemNaTela(MainActivity.this, EnumMensagemErro.MENSAGEM_ERRO_GENERICO.getMsg());
            }








    }
}
