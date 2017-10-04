package br.com.insidegroup.insidecomerce.util;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.com.insidegroup.insidecomerce.R;
import br.com.insidegroup.insidecomerce.entidades.Ticket;


public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolderTicket> implements View.OnClickListener {

    private List<Ticket> dados;
    private View.OnClickListener listener;


    public TicketAdapter(List<Ticket> dados) {
        this.dados = dados;

    }

    @Override
    public TicketAdapter.ViewHolderTicket onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_ticket, parent, false);

        ViewHolderTicket holderTicket =new ViewHolderTicket(view);
        view.setOnClickListener(this);

        return holderTicket;
    }

    @Override
    public void onBindViewHolder(TicketAdapter.ViewHolderTicket holder, int position) {


        if ((dados != null) && (dados.size() > 0)){
            Ticket Ticket = dados.get(position);
            holder.txtNome.setText(Ticket.getDataCriacao() + " - " + Ticket.getNome());
            holder.txtDescricao.setText(Ticket.getDescricao());
            holder.txtStatus.setText(Ticket.getStatus());
            if(Ticket.getStatus() == "Em Aberto"){
                holder.ticket.setBackgroundColor(Color.parseColor("#1dd11a"));
            }else if(Ticket.getStatus() == "Em Andamento"){
                holder.ticket.setBackgroundColor(Color.parseColor("#fff155"));
            }else if(Ticket.getStatus() == "Contrato Fechado"){
                holder.ticket.setBackgroundColor(Color.parseColor("#AA2426C6"));
            }else if(Ticket.getStatus() == "Nâo Fechou Contrato"){
                holder.ticket.setBackgroundColor(Color.parseColor("#a3ed0f1f"));
            }else if(Ticket.getStatus() == "Vistoria Reagendada"){
                holder.ticket.setBackgroundColor(Color.parseColor("#adf29d0a"));
            }else if(Ticket.getStatus() == "Cliente Cancelou Vistoria"){
                holder.ticket.setBackgroundColor(Color.parseColor("#ad999595"));
            }
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
        public TextView txtStatus;
        public LinearLayout ticket;


        public ViewHolderTicket(View intentView) {

            super(intentView);

            txtNome = (TextView)itemView.findViewById(R.id.txtNome);
            txtDescricao = (TextView)itemView.findViewById(R.id.txtDescricao);
            txtStatus = (TextView)itemView.findViewById(R.id.txtStatus);
            ticket = (LinearLayout)itemView.findViewById(R.id.linearLayoutTicket);




        }

    }

}



