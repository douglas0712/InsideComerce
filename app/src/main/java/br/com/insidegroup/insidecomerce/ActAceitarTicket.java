package br.com.insidegroup.insidecomerce;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActAceitarTicket extends AppCompatActivity {

    private AlertDialog alerta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aceitar_ticket);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Botão Aceitar
        Button buttonAceitar = (Button) findViewById(R.id.buttonAceitar);
        buttonAceitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Botão Recusar - Chama janela para informar o motivo
        Button buttonRecusar = (Button) findViewById(R.id.buttonRecusar);
        buttonRecusar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogMotivo();
            }
        });

    }


    private void dialogMotivo() {
        //LayoutInflater é utilizado para inflar nosso layout em uma view.
        //-pegamos nossa instancia da classe
        LayoutInflater li = getLayoutInflater();

        //inflamos o layout alerta.xml na view
        View view = li.inflate(R.layout.dialog_motivo_recusa, null);
        //definimos para o botão do layout um clickListener
        view.findViewById(R.id.buttonConfirmar).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //exibe um Toast informativo.
                Toast.makeText(ActAceitarTicket.this, "Ticket Recusado", Toast.LENGTH_SHORT).show();
                //desfaz o alerta.
                alerta.dismiss();
                finish();
            }
        });

        view.findViewById(R.id.buttonVoltar).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                alerta.dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Titulo");
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
    }


}
