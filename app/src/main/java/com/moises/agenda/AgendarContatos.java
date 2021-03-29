package com.moises.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import DAO.AgendarDAO;
import Model.AgendarModel;

public class AgendarContatos extends AppCompatActivity {

    EditText txtNomeContato;
    EditText txtTelefone;
    EditText txtEmail;
    EditText txtEndereco;
    Button   btnGravar;

    AgendarModel editarAgenda;
    AgendarModel agendarContatos;
    AgendarDAO agendarDAO;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar_contatos);

        agendarContatos = new AgendarModel();
        agendarDAO = new AgendarDAO(AgendarContatos.this);

        Intent intent = getIntent();
        editarAgenda = (AgendarModel) intent.getSerializableExtra("selectContatos");

        txtNomeContato = (EditText)findViewById(R.id.NomeContato);
        txtTelefone    = (EditText)findViewById(R.id.ContatoTelefone);
        txtEmail       = (EditText)findViewById(R.id.ContatoEmail);
        txtEndereco    = (EditText)findViewById(R.id.Endereco);
        btnGravar      = (Button)findViewById(R.id.btnCadastrar);



        //verifica se esta alterando ou se e novo cadastro

        if(editarAgenda != null){
            btnGravar.setText("alterar");
            txtNomeContato.setText(editarAgenda.getNome());
            txtTelefone.setText(String.valueOf(editarAgenda.getTelefone()));
            txtEmail.setText(editarAgenda.getEmail());
            txtEndereco.setText(editarAgenda.getEndereco());
            agendarContatos.setId(editarAgenda.getId());
        }
        else{
            btnGravar.setText("cadastrar");

        }


        //casa seja um novo cadastro

        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agendarContatos.setNome(txtNomeContato.getText().toString());
                agendarContatos.setTelefone(Integer.parseInt(txtTelefone.getText().toString()) );
                agendarContatos.setEmail(txtEmail.getText().toString());
                agendarContatos.setEndereco(txtEndereco.getText().toString());
                if(btnGravar.getText().toString().equals("cadastrar")){
                    //insert
                    agendarDAO.registrarcontato(agendarContatos);

                }
                else{
                    //update
                    agendarDAO.editarcontato(agendarContatos);
                }
                agendarDAO.close();
                finish();
            }
        });

    }
}