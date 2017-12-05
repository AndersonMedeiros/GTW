package com.example.andersonmedeiros.gtow;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.andersonmedeiros.gtow.BancoDeDados.BancoDados;
import com.example.andersonmedeiros.gtow.modelo.Material;
import com.example.andersonmedeiros.gtow.modelo.MaterialDao;
import com.example.andersonmedeiros.gtow.modelo.UsuarioDao;

public class Orcamento extends AppCompatActivity {
    private BancoDados bd;
    private SQLiteDatabase conn;
    private UsuarioDao usuarioDao;
    private MaterialDao materialDao;



    private ImageButton bt_parede;
    private ImageButton bt_forro;
    private ImageButton bt_tinta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orcamento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        try {
            bd = new BancoDados(this);
            conn = bd.getWritableDatabase();
            usuarioDao = new UsuarioDao(conn);
            materialDao = new MaterialDao(conn);

        } catch (SQLException ex){
            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setMessage("Falha ao abrir o banco!" + ex.getMessage());
            msg.setNeutralButton("OK", null);
            msg.show();
        }


        bt_parede = (ImageButton)findViewById(R.id.bt_parede);
        bt_forro = (ImageButton)findViewById(R.id.bt_forro);
        bt_tinta = (ImageButton)findViewById(R.id.bt_tinta);

        try {
            bt_parede.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String categoria = "Parede";
                    Class<Tijolo> context = Tijolo.class;
                    Intent it = new Intent(Orcamento.this, TiposMateriais.class);
                    it.putExtra("CATEGORIA", (String)categoria);
                    it.putExtra("CONTEXT", context);
                    startActivity(it);
                }
            });
            bt_forro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String categoria = "Forro";
                    Class<Forro> context = Forro.class;
                    Intent it = new Intent(Orcamento.this, TiposMateriais.class);
                    it.putExtra("CATEGORIA", (String)categoria);
                    it.putExtra("CONTEXT", context);
                    startActivity(it);
                }
            });
            bt_tinta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(Orcamento.this, Tinta.class);
                    startActivity(it);
                }
            });

        }catch (Exception ex){

            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setMessage(ex.getMessage());
            msg.setNeutralButton("OK", null);
            msg.show();
        }
    }

}
