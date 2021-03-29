package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import Model.AgendarModel;

public class AgendarDAO extends SQLiteOpenHelper {


    //variaveis constatis
    private static final String DATABASE = "Agenda";//nome banco
    private static final int VERSION = 1;//versionamento banco
    public String msmErro = "";//erro retorn


    //metodos
    public AgendarDAO(Context context){
        super(context, DATABASE, null, VERSION);
        msmErro = "";
    }


   //criar tabela banco
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE agenda("+
                "id   INTERGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                "nome TEXT NOT NULL, "+
                "telefone INTERGER NOT NULL, "+
                "email TEXT NOT NULL,"+
                "endereco TEXT NOT NULL);";
        db.execSQL(sql);

    }


    //vericar vercinamento
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion != newVersion){
            String sql = "DROP TABLE IF EXISTS agenda";
            db.execSQL(sql);
        }
    }


    //insert
    public boolean registrarcontato(AgendarModel agenda){

        try{
            ContentValues values = new ContentValues();
            values.put("nome", agenda.getNome());
            values.put("telefone", agenda.getTelefone());
            values.put("email", agenda.getEmail());
            values.put("endereco",agenda.getEndereco());
            getWritableDatabase().insert("agenda",null,values);
            return true;



        }
        catch (Exception ex){
            msmErro = ex.getMessage();
            return  false;
        }

    }


    //update
    public boolean editarcontato(AgendarModel agenda){

        try{
            ContentValues values = new ContentValues();
            values.put("nome", agenda.getNome());
            values.put("telefone", agenda.getTelefone());
            values.put("email", agenda.getEmail());
            values.put("endereco",agenda.getEndereco());

            String[] contatos={agenda.getId().toString()};

            getWritableDatabase().update("agenda",values, "id=?",contatos);
            return true;


        }
        catch (Exception ex){
            msmErro = ex.getMessage();
            return  false;
        }

    }



    //deletar
    public boolean deletarcontato (AgendarModel agenda){

        try{
            String[] contatos= {agenda.getId().toString()};
            getWritableDatabase().delete("agenda", "id=?",contatos);

             return true;
        }
        catch(Exception ex){
            msmErro = ex.getMessage();
            return false;

        }

    }



    //select
    public ArrayList<AgendarModel>contatos(){

     ArrayList<AgendarModel> contatos = new ArrayList<>();

     String[] Columns = {"id","nome","telefone","email","endereco"};
     Cursor cursor = getWritableDatabase().query("agenda",Columns, null, null, null, null, null);

     while(cursor.moveToNext()){
         AgendarModel contato = new AgendarModel();
         contato.setId( cursor.getLong(0));
         contato.setNome( cursor.getString(1));
         contato.setTelefone( cursor.getInt(2));
         contato.setEmail( cursor.getString(3));
         contato.setEndereco( cursor.getString(4));

         contatos.add(contato);
     }
     
     return contatos;

    }

}
