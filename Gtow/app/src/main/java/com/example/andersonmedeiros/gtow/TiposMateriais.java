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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.andersonmedeiros.gtow.BancoDeDados.BancoDados;
import com.example.andersonmedeiros.gtow.modelo.Material;
import com.example.andersonmedeiros.gtow.modelo.MaterialDao;
import com.example.andersonmedeiros.gtow.modelo.Usuario;
import com.example.andersonmedeiros.gtow.modelo.UsuarioDao;

import java.io.Serializable;
import java.util.List;

public class TiposMateriais extends AppCompatActivity {
    private BancoDados bd;
    private SQLiteDatabase conn;

    private Material material;
    private MaterialDao materialDao;

    private ListView lst_tipos_materiais;
    ArrayAdapter<Material> adpMateriais;
    List<Material> lstMateriais;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipos_materiais);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        try {
            bd = new BancoDados(this);
            conn = bd.getWritableDatabase();
            materialDao = new MaterialDao(conn);
        } catch (SQLException ex){
            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setMessage("Falha ao abrir o banco!" + ex.getMessage());
            msg.setNeutralButton("OK", null);
            msg.show();
        }

            Bundle bundle = getIntent().getExtras();
            if(bundle != null && bundle.containsKey("CATEGORIA") && bundle.containsKey("CONTEXT")){
                final String categoria = bundle.getString("CATEGORIA");
                final Class<?> context = bundle.get("CONTEXT").getClass();
                adpMateriais = materialDao.pesqMateriais(this, categoria);

                lst_tipos_materiais = (ListView)findViewById(R.id.lst_tipos_materiais);

                lst_tipos_materiais.setAdapter(adpMateriais);

                try {
                lst_tipos_materiais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Material material = adpMateriais.getItem(position);
                        Intent it = null;
                        if(material.getCategoria().equals("Parede"))
                            it = new Intent(TiposMateriais.this, Tijolo.class);
                        else if(material.getCategoria().equals("Forro"))
                            it = new Intent(TiposMateriais.this, Forro.class);

                        it.putExtra("MATERIAL", material);
                        startActivity(it);
                    }
                });}catch (Exception ex){
                    AlertDialog.Builder msg = new AlertDialog.Builder(this);
                    msg.setMessage(ex.getMessage());
                    msg.setNeutralButton("OK", null);
                    msg.show();
                }
            }


    }

}
