package com.example.andersonmedeiros.gtow;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.andersonmedeiros.gtow.BancoDeDados.BancoDados;
import com.example.andersonmedeiros.gtow.modelo.Material;
import com.example.andersonmedeiros.gtow.modelo.MaterialDao;
import com.example.andersonmedeiros.gtow.modelo.Usuario;
import com.example.andersonmedeiros.gtow.modelo.UsuarioDao;

public class CadastroMaterial extends AppCompatActivity {
    private BancoDados bd;
    private SQLiteDatabase conn;

    private Usuario usuario;
    private UsuarioDao usuarioDao;
    private Material material;
    private MaterialDao materialDao;

    private EditText edt_txt_id;
    private EditText edt_txt_nome;
    private EditText edt_txt_larg;
    private EditText edt_txt_comp;
    private EditText edt_txt_alt;
    private EditText edt_txt_esp;

    private Spinner sp_categoria;

    private Button bt_cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_material);
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
        /*
        edt_txt_id = (EditText)findViewById(R.id.edt_txt_Id);
        edt_txt_nome = (EditText)findViewById(R.id.edt_txt_nome);
        edt_txt_larg = (EditText)findViewById(R.id.edt_txt_larg);
        edt_txt_comp = (EditText)findViewById(R.id.edt_txt_comp);
        edt_txt_alt = (EditText)findViewById(R.id.edt_txt_alt);
        edt_txt_esp = (EditText)findViewById(R.id.edt_txt_esp);

        sp_categoria = (Spinner)findViewById(R.id.sp_categoria);
        preencherSpinnerCat();

        bt_cadastrar = (Button)findViewById(R.id.bt_cad_material);

        bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserir();
            }
        });*/

    }
    /*
    private void inserir(){
        try {
            material = new Material();

            material.setId(edt_txt_id.getText().toString());
            material.setNome(edt_txt_nome.getText().toString());
            //material.setCategoria(String.valueOf(sp_categoria.getSelectedItem()));
            material.setLargura(Double.parseDouble(edt_txt_larg.getText().toString()));
            material.setComprimento(Double.parseDouble(edt_txt_comp.getText().toString()));
            material.setAltura(Double.parseDouble(edt_txt_alt.getText().toString()));
            material.setEspessura(Double.parseDouble(edt_txt_esp.getText().toString()));

            materialDao.inserir(material);
        } catch (Exception ex){
            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setMessage("Erro ao cadastrar dados do material!" + ex.getMessage());
            msg.setNeutralButton("OK", null);
            msg.show();
        }

    }

    private void preencherSpinnerCat(){
        try {
            ArrayAdapter<String> adpSpCategoria = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);
            adpSpCategoria.add("Parede");
            adpSpCategoria.add("Forro");
            adpSpCategoria.setDropDownViewResource(android.R.layout.simple_spinner_item);
            sp_categoria.setAdapter(adpSpCategoria);
        }catch (Exception ex){
            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setMessage(ex.getMessage());
            msg.setNeutralButton("OK", null);
            msg.show();
        }
    }
    */
}
