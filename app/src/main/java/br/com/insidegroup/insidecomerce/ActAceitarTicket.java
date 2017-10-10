package br.com.insidegroup.insidecomerce;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.insidegroup.insidecomerce.entidades.AlteraStatusTicket;
import br.com.insidegroup.insidecomerce.entidades.Sessao;
import br.com.insidegroup.insidecomerce.entidades.Ticket;

public class ActAceitarTicket extends AppCompatActivity {

    private AlertDialog alerta;
    private static Sessao sessao = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aceitar_ticket);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.sessao = Sessao.getInstance();


        //Botão Aceitar
        Button buttonAceitar = (Button) findViewById(R.id.buttonContinuarAtendimento);
        buttonAceitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean alteraStatus = AlteraStatusTicket.AlteraStatusTicket(sessao.getTicket(), 2, "");
                Toast.makeText(ActAceitarTicket.this, "Ticket Aceito"+alteraStatus.toString(), Toast.LENGTH_SHORT).show();
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

        //Preenche os dados do ticket
        TextView eEndereco = (TextView) findViewById(R.id.textViewEndereco);
        TextView eBairro = (TextView) findViewById(R.id.textViewBairro);
        TextView eContato = (TextView) findViewById(R.id.textViewContato);

        eEndereco.setText(sessao.getTicket().getRua() + ", " + sessao.getTicket().getNumero());
        eBairro.setText(sessao.getTicket().getBairro() + " - "+ sessao.getTicket().getCidade());
        eContato.setText(sessao.getTicket().getNomeContato() + " - " + sessao.getTicket().getTelefoneContato());



    }


    private void dialogMotivo() {
        //LayoutInflater é utilizado para inflar nosso layout em uma view.
        //-pegamos nossa instancia da classe
        LayoutInflater li = getLayoutInflater();

        //inflamos o layout alerta.xml na view
        final View view = li.inflate(R.layout.dialog_motivo_recusa, null);
        //definimos para o botão do layout um clickListener
        view.findViewById(R.id.buttonConfirmar).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                EditText eMotivo = (EditText) view.findViewById(R.id.editTextMotivoRecusa);
                String motivo = eMotivo.getText().toString();
                Boolean alteraStatus = AlteraStatusTicket.AlteraStatusTicket(sessao.getTicket(), 7, motivo);
                alerta.dismiss();
                finish();
                Toast.makeText(ActAceitarTicket.this, "Ticket Recusado", Toast.LENGTH_SHORT).show();

            }
        });

        view.findViewById(R.id.buttonVoltar).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                alerta.dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Motivo parar Recusa do Ticket");
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
    }


}
