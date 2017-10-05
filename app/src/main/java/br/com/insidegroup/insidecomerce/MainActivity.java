package br.com.insidegroup.insidecomerce;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.insidegroup.insidecomerce.util.Util;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnEntrar = (Button) findViewById(R.id.buttonEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, ActListaTicket.class);
                startActivity(it);
            }
        });


        //Util.showNotification(MainActivity.this, "teste");


        //inicia o service
        //Context c = getApplicationContext();
        //Intent intent = new Intent(c, InsideService.class);
        //c.startService(intent);

    }
}
