package br.com.insidegroup.insidecomerce;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import br.com.insidegroup.insidecomerce.R;
import br.com.insidegroup.insidecomerce.entidades.AlteraStatusTicket;
import br.com.insidegroup.insidecomerce.entidades.InsereDataAgendamento;
import br.com.insidegroup.insidecomerce.entidades.Sessao;

public class ActTicketEmAndamento extends AppCompatActivity {

    private AlertDialog alerta;
    private int statusSelecionado = 0;
    private int dia, mes, ano, hora, minutos;
    private String horaSelect, dataSelect;
    private Sessao sessao;
    private InsereDataAgendamento insereDataAgendamento = new InsereDataAgendamento();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_em_andamento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.sessao = sessao.getInstance();
        //Botão Recusar - Chama janela para informar o motivo
        Button buttonContinuar = (Button) findViewById(R.id.buttonContinuarAtendimento);
        buttonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogStatus();
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


    private void dialogStatus() {
        //LayoutInflater é utilizado para inflar nosso layout em uma view.
        //-pegamos nossa instancia da classe
        statusSelecionado = 0;
        LayoutInflater li = getLayoutInflater();

        //inflamos o layout alerta.xml na view
        View view = li.inflate(R.layout.dialog_altera_status_ticket, null);
        //definimos para o botão do layout um clickListene
        view.findViewById(R.id.buttonConfirmar).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                if(statusSelecionado == 0){
                    Toast.makeText(ActTicketEmAndamento.this, "Selecione uma das opções", Toast.LENGTH_LONG).show();
                }else{
                    //Se for selecionado reagendada, chama modal de datas
                    if(statusSelecionado == 6){

                        final Calendar c = Calendar.getInstance();
                        dia = c.get(Calendar.DAY_OF_MONTH);
                        mes = c.get(Calendar.MONTH);
                        ano = c.get(Calendar.YEAR);
                        hora = c.get(Calendar.HOUR_OF_DAY);
                        minutos = c.get(Calendar.MINUTE);


                        DatePickerDialog datePickerDialog = new DatePickerDialog(ActTicketEmAndamento.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                dataSelect = dayOfMonth + "/" + (month + 1) + "/" + year;
                                alerta.dismiss();

                                TimePickerDialog timePickerDialog = new TimePickerDialog(ActTicketEmAndamento.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        horaSelect = hourOfDay + ":" + minute;
                                        AlteraStatusTicket.AlteraStatusTicket(sessao.getTicket(), 6, "");
                                        insereDataAgendamento.insereDataAgendamento(sessao.getTicket(), dataSelect, horaSelect);
                                        alerta.dismiss();
                                        finish();

                                    }
                                }, hora, minutos, false);
                                timePickerDialog.show();


                            }
                        }, ano, mes, dia);

                        datePickerDialog.show();

                    }
                    else if(statusSelecionado == 3){
                        AlteraStatusTicket.AlteraStatusTicket(sessao.getTicket(), 3, "");
                        alerta.dismiss();
                        finish();
                    }else if(statusSelecionado == 4){
                        AlteraStatusTicket.AlteraStatusTicket(sessao.getTicket(), 4, "");
                        alerta.dismiss();
                        finish();
                    }else if(statusSelecionado == 5){
                        AlteraStatusTicket.AlteraStatusTicket(sessao.getTicket(), 5 , "");
                        alerta.dismiss();
                        finish();
                    }

                }

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

        final CharSequence[] items = {"Cliente cancelou a Vistoria", "Cliente Recusou o Contrato", "Cliente Fechou Contrato", "Reagendar Atendimento"};
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {


                switch (item) {

                    case 0:
                        statusSelecionado = 3;
                        break;
                    case 1:
                        statusSelecionado = 4;
                        break;
                    case 2:
                        statusSelecionado = 5;
                        break;
                    case 3:
                        statusSelecionado = 6;
                        break;

                }

            }
            });


        alerta = builder.create();
        alerta.show();
    }

}
