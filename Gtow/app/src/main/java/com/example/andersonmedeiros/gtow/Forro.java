package com.example.andersonmedeiros.gtow;

import android.annotation.SuppressLint;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andersonmedeiros.gtow.BancoDeDados.BancoDados;
import com.example.andersonmedeiros.gtow.modelo.Material;
import com.example.andersonmedeiros.gtow.modelo.MaterialDao;

import java.io.Serializable;
import java.math.BigDecimal;

public class Forro extends AppCompatActivity {
    private BancoDados bd;
    private SQLiteDatabase conn;

    private Material material;
    private MaterialDao materialDao;

    private EditText edt_txt_larg_mat;
    private EditText edt_txt_comp_mat;
    private EditText edt_txt_larg_amb;
    private EditText edt_txt_comp_amb;

    private TextView tv_n_mat;

    private RadioGroup rg_dir;
    private RadioButton rb_checked;

    private Button bt_calcular;

    private double comp_amb, larg_amb;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forro);
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
        tv_n_mat = (TextView)findViewById(R.id.tv_n_mat);

        edt_txt_comp_mat = (EditText)findViewById(R.id.edt_txt_comp_mat);
        edt_txt_larg_mat = (EditText)findViewById(R.id.edt_txt_larg_mat);
        edt_txt_comp_amb = (EditText)findViewById(R.id.edt_txt_comp_amb);
        edt_txt_larg_amb = (EditText)findViewById(R.id.edt_txt_larg_amb);

        rg_dir = (RadioGroup)findViewById(R.id.rg_dir);

        try {
            Bundle bundle = getIntent().getExtras();

            if ((bundle != null) && bundle.containsKey("MATERIAL")) {
                material = (Material) bundle.getSerializable("MATERIAL");
                tv_n_mat.setText(material.getNome());
                edt_txt_comp_mat.setText(String.valueOf(material.getComprimento()));
                edt_txt_larg_mat.setText(String.valueOf(material.getLargura()));
                edt_txt_comp_mat.setEnabled(false);
                edt_txt_larg_mat.setEnabled(false);
                edt_txt_comp_amb.requestFocus();
            }
        }catch (Exception ex){
            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setMessage(ex.getMessage());
            msg.setNeutralButton("OK", null);
            msg.show();

        }

        bt_calcular = (Button)findViewById(R.id.bt_calcular);
        bt_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comp_amb = Double.parseDouble(edt_txt_comp_amb.getText().toString());
                larg_amb = Double.parseDouble(edt_txt_larg_amb.getText().toString());
                int id = rg_dir.getCheckedRadioButtonId();
                rb_checked = rg_dir.findViewById(id);

                Intent it = new Intent(Forro.this, ResultForro.class);
                it.putExtra("MATERIAL", material);
                it.putExtra("COMP_AMB", (double) comp_amb);
                it.putExtra("LARG_AMB", (double) larg_amb);
                it.putExtra("INST", String.valueOf(rb_checked.getText().toString()));

                startActivity(it);
            }
        });

    }

}
