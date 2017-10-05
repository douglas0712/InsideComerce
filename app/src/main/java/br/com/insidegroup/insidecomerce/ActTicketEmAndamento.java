package br.com.insidegroup.insidecomerce;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import br.com.insidegroup.insidecomerce.R;

public class ActTicketEmAndamento extends AppCompatActivity {

    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_em_andamento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Botão Recusar - Chama janela para informar o motivo
        Button buttonContinuar = (Button) findViewById(R.id.buttonContinuarAtendimento);
        buttonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogStatus();
            }
        });


    }


    private void dialogStatus() {
        //LayoutInflater é utilizado para inflar nosso layout em uma view.
        //-pegamos nossa instancia da classe
        LayoutInflater li = getLayoutInflater();

        //inflamos o layout alerta.xml na view
        View view = li.inflate(R.layout.dialog_altera_status_ticket, null);
        //definimos para o botão do layout um clickListener
        view.findViewById(R.id.buttonConfirmar).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //exibe um Toast informativo.
                Toast.makeText(ActTicketEmAndamento.this, "Ação Confirmada", Toast.LENGTH_SHORT).show();
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
        builder.setTitle("Sequencia de Atendimento");
        builder.setView(view);

        final CharSequence[] items = {"Reagendar Atendimento","Cliente cancelou a Vistoria","Cliente Fechou Contrato"};
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {


                switch (item) {
                    case 0:
                        // Your code when first option seletced
                        break;
                    case 1:
                        // Your code when 2nd  option seletced

                        break;
                    case 2:
                        // Your code when 3rd option seletced
                        break;

                }
                alerta.dismiss();
            }
            });


        alerta = builder.create();
        alerta.show();
    }


}
