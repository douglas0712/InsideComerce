package br.com.insidegroup.insidecomerce.util;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.insidegroup.insidecomerce.R;

public class DataHora extends AppCompatActivity {

    Button bdata, bhora;
    TextView edata, ehora;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_hora);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bdata = (Button) findViewById(R.id.bdata);
        bhora = (Button) findViewById(R.id.bhora);
        edata = (TextView) findViewById(R.id.textData);
        ehora = (TextView) findViewById(R.id.textHora);




    }

}
