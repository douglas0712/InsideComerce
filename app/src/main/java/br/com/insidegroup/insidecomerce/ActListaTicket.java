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

import java.util.ArrayList;
import java.util.List;

import br.com.insidegroup.insidecomerce.entidades.Ticket;
import br.com.insidegroup.insidecomerce.util.TicketAdapter;


public class ActListaTicket extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private RecyclerView recicleViewListaDados;
    private ConstraintLayout layoutContent;
    private TicketAdapter ticketAdapter;

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


            final List<Ticket> lstTicket = new ArrayList<>();

            Ticket ticket1= new Ticket();
            ticket1.setNome("Ticket 1");
            ticket1.setDescricao("Rua XXXXXXXXXX, 1231");
            ticket1.setDataCriacao("01/01/2017");
            ticket1.setStatus("Em Aberto");
            lstTicket.add(ticket1);


            Ticket ticket2= new Ticket();
            ticket2.setNome("Ticket 2");
            ticket2.setDescricao("Rua YYYYYYYYYY, 9988");
            ticket2.setDataCriacao("01/01/2017");
            ticket2.setStatus("Em Andamento");
            lstTicket.add(ticket2);


            Ticket ticket3= new Ticket();
            ticket3.setNome("Ticket 3");
            ticket3.setDescricao("Rua GGGGGGG, 9988");
            ticket3.setDataCriacao("01/01/2017");
            ticket3.setStatus("Contrato Fechado");
            lstTicket.add(ticket3);

            Ticket ticket4= new Ticket();
            ticket4.setNome("Ticket 4");
            ticket4.setDescricao("Rua GGGGGGG, 9988");
            ticket4.setDataCriacao("01/01/2017");
            ticket4.setStatus("Nâo Fechou Contrato");
            lstTicket.add(ticket4);

            Ticket ticket5= new Ticket();
            ticket5.setNome("Ticket 5");
            ticket5.setDescricao("Rua GGGGGGG, 9988");
            ticket5.setDataCriacao("01/01/2017");
            ticket5.setStatus("Vistoria Reagendada");
            lstTicket.add(ticket5);

            Ticket ticket6= new Ticket();
            ticket6.setNome("Ticket 6");
            ticket6.setDescricao("Rua GGGGGGG, 9988");
            ticket6.setDataCriacao("01/01/2017");
            ticket6.setStatus("Cliente Cancelou Vistoria");
            lstTicket.add(ticket6);




        TicketAdapter ticketAdapter= new TicketAdapter(lstTicket);
            recicleViewListaDados.setAdapter(ticketAdapter);

            ticketAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ticketSelecionado = lstTicket.get(recicleViewListaDados.getChildAdapterPosition(view));
                    Intent it = new Intent(ActListaTicket.this, ActAceitarTicket.class);
                    startActivity(it);

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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Agenda) {
            // Handle the camera action
        } else if (id == R.id.ListaTicket) {

        } else if (id == R.id.Sair) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
