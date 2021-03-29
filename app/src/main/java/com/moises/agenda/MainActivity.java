package com.moises.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import DAO.AgendarDAO;
import Model.AgendarModel;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button btnAgendar;
    AgendarDAO agendarDAO;
    ArrayList<AgendarModel> contatos;
    ArrayAdapter adapter;
    AgendarModel contato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView)findViewById(R.id.Contatos);
        btnAgendar = (Button)findViewById(R.id.btnAgendar);
        btnAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AgendarContatos.class);
                startActivity(intent);

            }
        });

        // listar list view

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                contato = (AgendarModel) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, AgendarContatos.class);
                intent.putExtra("selectContato",contato);
                startActivity(intent);

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                contato = (AgendarModel) parent.getItemAtPosition(position);
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarContatosAgenda();
    }


    //carregar banco de dados

    public void carregarContatosAgenda(){

        agendarDAO = new AgendarDAO(MainActivity.this);
        contatos = agendarDAO.contatos();

        agendarDAO.close();

        if(contatos != null){

            adapter = new ArrayAdapter<AgendarModel>(MainActivity.this, android.R.layout.simple_list_item_1,contatos);

            listView.setAdapter(adapter);
        }


    }


}