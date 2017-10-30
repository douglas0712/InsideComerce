package br.com.insidegroup.insidecomerce;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import net.sourceforge.jtds.jdbc.DateTime;

import org.w3c.dom.Text;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import br.com.insidegroup.insidecomerce.DAO.DAOEventos;
import br.com.insidegroup.insidecomerce.entidades.Sessao;
import br.com.insidegroup.insidecomerce.util.EnumMensagemErro;
import br.com.insidegroup.insidecomerce.util.Util;

public class ActAgenda2 extends AppCompatActivity {

    CompactCalendarView compactCalendar;
    TextView textViewListaEventos;
    TextView textViewTituloEvento;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());
    private static Sessao sessao = null;
    List<String> lstEventos = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda2);
        this.sessao = sessao.getInstance();



        //===========BARRA TITULO========================
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        Calendar cal = GregorianCalendar.getInstance();
        int anoAtual = cal.get(Calendar.YEAR);
        int mesAtual = cal.get(Calendar.MONTH) +1;
        actionBar.setTitle(descricaoMes(mesAtual) + " - " + anoAtual);

        textViewListaEventos = (TextView) findViewById(R.id.textViewListaeventos);
        textViewTituloEvento = (TextView) findViewById(R.id.textViewTituloEvento);
        compactCalendar = (CompactCalendarView) findViewById(R.id.compactCalendar);
        compactCalendar.setUseThreeLetterAbbreviation(true);

        //=================SETA AS DATAS COM EVENTOS============================
        try {
            lstEventos = DAOEventos.getListaEventos(sessao.getCodUsuario());
            //separa data da hora

            for(int i = 0; i<lstEventos.size(); i++){
                String dataEvt = lstEventos.get(i).split(";")[0].replace("/", ".");
                String horaEvt = lstEventos.get(i).split(";")[1];


                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
                formatter.setLenient(false);

                Date curDate = new Date();
                long curMillis = curDate.getTime();
                String curTime = formatter.format(curDate);

                String oldTime = dataEvt + ", "+horaEvt;
                Date oldDate = formatter.parse(oldTime);

                Event ev1 = new Event(Color.RED, oldDate.getTime(), "Teste:" +i);
                compactCalendar.addEvent(ev1);

            }


        } catch (ParseException e) {
            Util.ExibirMensagemNaTela(this, EnumMensagemErro.MENSAGEM_ERRO_GENERICO.getMsg());
        } catch (SQLException e) {
            Util.ExibirMensagemNaTela(this, EnumMensagemErro.MENSAGEM_ERRO_GENERICO.getMsg());
        }



        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();
                String dia = String.valueOf(dateClicked.getDate());
                String mes = String.valueOf(dateClicked.getMonth() +1);
                String ano = String.valueOf(dateClicked.getYear() + 1900);
                Long b = dateClicked.getTime();


                if(mes.length() == 1) mes = "0" + mes;
                if(dia.length() == 1) dia = "0" + dia;


                String dataSelect = dia+"/"+mes+"/"+ano;

                textViewTituloEvento.setText(dataSelect);
                textViewListaEventos.setText("");
                int i = 0;
                while(i < lstEventos.size()){
                    if(lstEventos.get(i).split(";")[0].equals(dataSelect)){
                        String a = textViewListaEventos.getText().toString();
                        textViewListaEventos.setText(a + "\n  " + lstEventos.get(i).split(";")[1]+" - "+ lstEventos.get(i).split(";")[2]);
                    }
                    i++;
                }


            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                int ano = firstDayOfNewMonth.getYear() + 1900;
                int mes = firstDayOfNewMonth.getMonth() + 1;
                String mesDescricao = descricaoMes(mes);


                actionBar.setTitle(mesDescricao + " - " + ano);
                textViewListaEventos.setText("");
                textViewTituloEvento.setText("");

            }
        });
    }

    private String descricaoMes(int mes){


        String mesDescricao = "";
        switch(mes){
            case 1:
                mesDescricao = "Janeiro";
                break;
            case 2:
                mesDescricao = "Fevereiro";
                break;
            case 3:
                mesDescricao = "Março";
                break;
            case 4:
                mesDescricao = "Abril";
                break;
            case 5:
                mesDescricao = "Maio";
                break;
            case 6:
                mesDescricao = "Junho";
                break;
            case 7:
                mesDescricao = "Julho";
                break;
            case 8:
                mesDescricao = "Agosto";
                break;
            case 9:
                mesDescricao = "Setembro";
                break;
            case 10:
                mesDescricao = "Outubro";
                break;
            case 11:
                mesDescricao = "Novembro";
                break;
            case 12:
                mesDescricao = "Dezembro";
                break;
        }

        return mesDescricao;

    }
}