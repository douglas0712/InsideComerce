package br.com.insidegroup.insidecomerce.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.com.insidegroup.insidecomerce.ActListaTicket;
import br.com.insidegroup.insidecomerce.MainActivity;
import br.com.insidegroup.insidecomerce.R;
import br.com.insidegroup.insidecomerce.TicketHistorico;
import br.com.insidegroup.insidecomerce.entidades.DescricaoStatuTicket;
import br.com.insidegroup.insidecomerce.entidades.Sessao;
import br.com.insidegroup.insidecomerce.entidades.Ticket;


public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolderTicket> implements View.OnClickListener {

    private List<Ticket> dados;
    private View.OnClickListener listener;
    private Context context;
    private Sessao sessao;

    public TicketAdapter(List<Ticket> dados, Context context) {
        this.dados = dados;
        this.context = context;

    }

    @Override
    public TicketAdapter.ViewHolderTicket onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        this.context = parent.getContext();
        View view = layoutInflater.inflate(R.layout.item_ticket, parent, false);
        this.sessao.getInstance();
        ViewHolderTicket holderTicket =new ViewHolderTicket(view);
        view.setOnClickListener(this);

        return holderTicket;
    }

    @Override
    public void onBindViewHolder(TicketAdapter.ViewHolderTicket holder, final int position) {



        if ((dados == null) || (dados.size() <= 0))
            return;

        final Ticket Ticket = dados.get(position);
        String descricaoStatus = DescricaoStatuTicket.descricaoStatusTicket(Ticket.getStatus());

        holder.historico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessao.setTicket(Ticket);
                Intent i = new Intent(context, TicketHistorico.class);
                context.startActivity(i);
            }
        });



        holder.txtNome.setText(Ticket.getDataCriacao() + " - " + descricaoStatus);
        holder.txtDescricao.setText(Ticket.getRua() + ", "+ Ticket.getNumero() + "\n"+ Ticket.getBairro());
        //holder.txtStatus.setText(Ticket.getStatus());
        if(Ticket.getStatus() == 1){
            holder.coresStatus.setBackgroundColor(Color.parseColor("#FFB1FF84"));
        }else if(Ticket.getStatus() == 2){
            holder.coresStatus.setBackgroundColor(Color.parseColor("#FFFFF68F"));
        }else if(Ticket.getStatus() == 3){
            holder.coresStatus.setBackgroundColor(Color.parseColor("#FFCED2D9"));
        }else if(Ticket.getStatus() == 4){
            holder.coresStatus.setBackgroundColor(Color.parseColor("#FFFF566C"));
        }else if(Ticket.getStatus() == 5){
            holder.coresStatus.setBackgroundColor(Color.parseColor("#FF9FC3F5"));
        }else if(Ticket.getStatus() == 6){
            holder.coresStatus.setBackgroundColor(Color.parseColor("#FFFFAF68"));
        }else if(Ticket.getStatus() == 7){
            holder.coresStatus.setBackgroundColor(Color.parseColor("#FF383838"));
        }


    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
    public void setOnClickListener (View.OnClickListener listener) {
        this.listener = listener;

    }

    @Override
    public void onClick(View view) {
        if (listener != null)
            listener.onClick(view);
    }

    public class ViewHolderTicket extends RecyclerView.ViewHolder{

        public TextView txtNome;
        public TextView txtDescricao;
        public LinearLayout ticket;
        public TextView coresStatus;
        public Button btnteste;
        public ImageButton historico;



        public ViewHolderTicket(View intentView) {

            super(intentView);

            txtNome = (TextView)itemView.findViewById(R.id.txtNome);
            txtDescricao = (TextView)itemView.findViewById(R.id.txtDescricao);
            ticket = (LinearLayout)itemView.findViewById(R.id.linearLayoutTicket);
            coresStatus = (TextView) itemView.findViewById(R.id.textCores);

            historico = (ImageButton) itemView.findViewById(R.id.imageButton);

        }

    }

}



