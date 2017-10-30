package br.com.insidegroup.insidecomerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.insidegroup.insidecomerce.Controles.Conexao;
import br.com.insidegroup.insidecomerce.Controles.ControleTicket;
import br.com.insidegroup.insidecomerce.DAO.DAOTIcket;
import br.com.insidegroup.insidecomerce.entidades.Sessao;
import br.com.insidegroup.insidecomerce.entidades.Ticket;
import br.com.insidegroup.insidecomerce.util.Erro;
import br.com.insidegroup.insidecomerce.util.TicketAdapter;
import br.com.insidegroup.insidecomerce.util.Util;


public class ActListaTicket extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private RecyclerView recicleViewListaDados;
    private ConstraintLayout layoutContent;

    public static Ticket ticketSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_lista_ticket);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Setar adaptador cardview na tela
        recicleViewListaDados = (RecyclerView) findViewById(R.id.recycleListaTicket);
        layoutContent = (ConstraintLayout) findViewById(R.id.layoutContent);
        LinearLayoutManager linear = new LinearLayoutManager(this);
        recicleViewListaDados.setLayoutManager(linear);


        ExibirListaTicket();

    }

    public void ExibirListaTicket() {


        ActListaTicket.this.setTitle("Lista de Tickets");
        final Sessao sessao= Sessao.getInstance();



        try {
            List<Ticket> lstTicket = ControleTicket.getListaTicket(sessao.getCodUsuario());
            setarListaTicketNoAdaptador(lstTicket, sessao);
        } catch (Erro erro) {

            Util.ExibirMensagemNaTela(ActListaTicket.this, erro.getMsg());
        }



    }


    public void setarListaTicketNoAdaptador(final List<Ticket> lstTicket, final Sessao sessao) {

        TicketAdapter ticketAdapter= new TicketAdapter(lstTicket, this);
        recicleViewListaDados.setAdapter(ticketAdapter);

        ticketAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketSelecionado = lstTicket.get(recicleViewListaDados.getChildAdapterPosition(view));
                if(ticketSelecionado.getStatus() == 1) {
                    sessao.setTicket(ticketSelecionado);
                    Intent it = new Intent(ActListaTicket.this, ActAceitarTicket.class);
                    startActivity(it);
                }else if(ticketSelecionado.getStatus() == 2) {
                    sessao.setTicket(ticketSelecionado);
                    Intent it = new Intent(ActListaTicket.this, ActTicketEmAndamento.class);
                    startActivity(it);
                }else if(ticketSelecionado.getStatus() == 3) {

                }else if(ticketSelecionado.getStatus() == 4) {

                }else if(ticketSelecionado.getStatus() == 5) {

                }else if(ticketSelecionado.getStatus() == 6) {
                    sessao.setTicket(ticketSelecionado);
                    Intent it = new Intent(ActListaTicket.this, ActTicketEmAndamento.class);
                    startActivity(it);

                }else if(ticketSelecionado.getStatus() == 7) {

                }

            }
        });

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.act_lista_ticket, menu);
        return true;
    }



    @Override
    public void onResume() {
        super.onResume();

        Toast.makeText(getApplicationContext(), "Atualizando!", Toast.LENGTH_SHORT).show();
        ExibirListaTicket();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Agenda) {
            Intent it = new Intent(ActListaTicket.this, ActAgenda2.class);
            startActivity(it);
        } else if (id == R.id.Sair) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
