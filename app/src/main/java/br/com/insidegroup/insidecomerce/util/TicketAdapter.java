package br.com.insidegroup.insidecomerce.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
            holder.txtNome.setText(Ticket.getNome());
            holder.txtDescricao.setText(Ticket.getDescricao());

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
        public ImageView imgOfinica;


        public ViewHolderTicket(View intentView) {

            super(intentView);

            txtNome = (TextView)itemView.findViewById(R.id.txtNome);
            txtDescricao = (TextView)itemView.findViewById(R.id.txtDescricao);


        }

    }

}


