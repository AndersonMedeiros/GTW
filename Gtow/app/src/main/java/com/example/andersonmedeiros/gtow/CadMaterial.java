package com.example.andersonmedeiros.gtow;

import android.app.AlertDialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.andersonmedeiros.gtow.BancoDeDados.BancoDados;
import com.example.andersonmedeiros.gtow.modelo.Material;
import com.example.andersonmedeiros.gtow.modelo.MaterialDao;
import com.example.andersonmedeiros.gtow.modelo.Usuario;
import com.example.andersonmedeiros.gtow.modelo.UsuarioDao;

import java.util.List;

public class CadMaterial extends AppCompatActivity {
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
        setContentView(R.layout.activity_cad_material);
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

        edt_txt_id = (EditText)findViewById(R.id.edt_txt_Id);
        edt_txt_nome = (EditText)findViewById(R.id.edt_txt_nome);
        edt_txt_larg = (EditText)findViewById(R.id.edt_txt_larg);
        edt_txt_comp = (EditText)findViewById(R.id.edt_txt_comp);
        edt_txt_alt = (EditText)findViewById(R.id.edt_txt_alt);
        edt_txt_esp = (EditText)findViewById(R.id.edt_txt_esp);

        sp_categoria = (Spinner)findViewById(R.id.sp_categoria);
        ArrayAdapter<CharSequence> adp_categoria_mat = ArrayAdapter.createFromResource(this, R.array.adp_categoria_material, android.R.layout.simple_spinner_item);
        adp_categoria_mat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_categoria.setAdapter(adp_categoria_mat);

        bt_cadastrar = (Button)findViewById(R.id.bt_cad_material);

        bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserir();
            }
        });
    }

    private void inserir(){
        try{
            material =  new Material();
            material.setId(edt_txt_id.getText().toString());
            material.setNome(edt_txt_nome.getText().toString());
            material.setCategoria(String.valueOf(sp_categoria.getSelectedItem()));
            if (edt_txt_larg.getText().toString() != null && !edt_txt_larg.getText().toString().equals(""))
                material.setLargura(Double.parseDouble(edt_txt_larg.getText().toString()));
            if (edt_txt_comp.getText().toString() != null && !edt_txt_comp.getText().toString().equals(""))
                material.setComprimento(Double.parseDouble(edt_txt_comp.getText().toString()));
            if (edt_txt_alt.getText().toString() != null && !edt_txt_alt.getText().toString().equals(""))
                material.setAltura(Double.parseDouble(edt_txt_alt.getText().toString()));
            if (edt_txt_esp.getText().toString() != null && !edt_txt_esp.getText().toString().equals(""))
                material.setEspessura(Double.parseDouble(edt_txt_esp.getText().toString()));

            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setMessage("Material cadastrado com sucesso!");
            msg.setNeutralButton("OK", null);
            msg.show();

            materialDao.inserir(material);
        } catch (Exception ex){
            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setMessage("Erro ao inseir dados do usuário!" + ex.getMessage());
            msg.setNeutralButton("OK", null);
            msg.show();
        }
    }

}
