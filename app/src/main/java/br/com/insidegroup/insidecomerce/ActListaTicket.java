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
import br.com.insidegroup.insidecomerce.entidades.Sessao;
import br.com.insidegroup.insidecomerce.entidades.Ticket;
import br.com.insidegroup.insidecomerce.util.TicketAdapter;


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
        final List<Ticket> lstTicket = new ArrayList<>();
        final Sessao sessao = Sessao.getInstance();

        Connection con = Conexao.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "";
        try {
            if(con == null){
                Toast.makeText(ActListaTicket.this, "Verifique sua conexão.", Toast.LENGTH_SHORT).show();
            }
            stmt = con.createStatement();


            sql = "SELECT * FROM TICKET WHERE VENDEDORVINCULADO = '"+ sessao.getCodUsuario() +"'";
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                Ticket ticket = new Ticket();
                ticket.setStatus(rs.getInt("status"));
                ticket.setRua(rs.getString("rua"));
                ticket.setNumero(rs.getString("numero"));
                ticket.setBairro(rs.getString("bairro"));
                ticket.setCidade(rs.getString("cidade"));
                ticket.setEstado(rs.getString("estado"));
                ticket.setDataCriacao(rs.getString("datacriacao"));
                ticket.setNomeContato(rs.getString("nomeContato"));
                ticket.setTelefoneContato(rs.getString("telefoneContato"));
                ticket.setIdTicket(rs.getInt("id"));
                lstTicket.add(ticket);

            }



        } catch (SQLException e) {
            e.printStackTrace();
        }



        TicketAdapter ticketAdapter= new TicketAdapter(lstTicket);
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Agenda) {
            Intent it = new Intent(ActListaTicket.this, ActAgenda.class);
            startActivity(it);
        } else if (id == R.id.ListaTicket) {

        } else if (id == R.id.Sair) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
