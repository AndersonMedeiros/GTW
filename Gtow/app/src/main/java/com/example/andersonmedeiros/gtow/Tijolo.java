package com.example.andersonmedeiros.gtow;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.andersonmedeiros.gtow.BancoDeDados.BancoDados;
import com.example.andersonmedeiros.gtow.modelo.Material;
import com.example.andersonmedeiros.gtow.modelo.MaterialDao;
import com.example.andersonmedeiros.gtow.modelo.Usuario;

import java.io.Serializable;

public class Tijolo extends AppCompatActivity {
    private BancoDados bd;
    private SQLiteDatabase conn;

    private Material material;
    private MaterialDao materialDao;

    private TextView tv_n_mat;
    private TextView tv_n_amb;

    private EditText edt_txt_comp_mat;
    private EditText edt_txt_larg_mat;
    private EditText edt_txt_alt_mat;
    private EditText edt_txt_comp_amb;
    private EditText edt_txt_larg_amb;
    private EditText edt_txt_alt_amb;
    private StringBuilder nota = new StringBuilder();
    private double comp_amb, larg_amb, alt_amb, area;
    private Button bt_calcular;

    private LinearLayout ll_amb;
    private LinearLayout ll;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tijolo);
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
        tv_n_amb = (TextView)findViewById(R.id.tv_n_amb);

        edt_txt_comp_mat = (EditText)findViewById(R.id.edt_txt_comp_mat);
        edt_txt_larg_mat = (EditText)findViewById(R.id.edt_txt_larg_mat);
        edt_txt_alt_mat = (EditText)findViewById(R.id.edt_txt_alt_mat);
        edt_txt_comp_amb = (EditText)findViewById(R.id.edt_txt_comp_amb);
        edt_txt_larg_amb = (EditText)findViewById(R.id.edt_txt_larg_amb);
        edt_txt_alt_amb = (EditText)findViewById(R.id.edt_txt_alt_amb);
        ll_amb = (LinearLayout)findViewById(R.id.ll_ambiente);
        ll = (LinearLayout)findViewById(R.id.ll);

        bt_calcular = (Button)findViewById(R.id.bt_calcular);
        Bundle bundle = getIntent().getExtras();

        if((bundle!=null) && bundle.containsKey("MATERIAL")){
            material = (Material) bundle.getSerializable("MATERIAL");
            tv_n_mat.setText(material.getNome());
            edt_txt_comp_mat.setText(String.valueOf(material.getComprimento()));
            edt_txt_larg_mat.setText(String.valueOf(material.getLargura()));
            edt_txt_alt_mat.setText(String.valueOf(material.getAltura()));
            edt_txt_comp_mat.setEnabled(false);
            edt_txt_larg_mat.setEnabled(false);
            edt_txt_alt_mat.setEnabled(false);
            edt_txt_comp_amb.requestFocus();
        }

       /* comp_amb = Double.parseDouble(edt_txt_comp_amb.getText().toString());
        larg_amb = Double.parseDouble(edt_txt_larg_amb.getText().toString());
        alt_amb = Double.parseDouble(edt_txt_alt_amb.getText().toString());

        area = comp_amb * larg_amb;



        nota.append("*********\n");
        nota.append("Material\n");
        nota.append(material.getNome()+": "+material.getLargura()+"x"+material.getComprimento()+"x"+material.getAltura()+"\n\n");
        nota.append("-----------\n");
        nota.append("Ambiente\n");
        nota.append("Comprimento: " + edt_txt_comp_amb.getText().toString() + " m2\n" +
                    "Largura: " + edt_txt_larg_amb.getText().toString() + " m2\n" +
                    "Altura: " + edt_txt_alt_amb.getText().toString() + " m2\n" +
                    "Area Total: " + area + "m2\n");
        nota.append("-----------\n");*/

        bt_calcular.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                try {

                    comp_amb = Double.parseDouble(edt_txt_comp_amb.getText().toString());
                    larg_amb = Double.parseDouble(edt_txt_larg_amb.getText().toString());
                    alt_amb = Double.parseDouble(edt_txt_alt_amb.getText().toString());

                    area = comp_amb * larg_amb;

                    nota.append("*********\n");
                    nota.append("Material\n");
                    nota.append(material.getNome()+": "+material.getLargura()+"x"+material.getComprimento()+"x"+material.getAltura()+"\n\n");
                    nota.append("-----------\n");
                    nota.append("Dados Gerais\n");
                    nota.append("Comprimento: " + edt_txt_comp_amb.getText().toString() + " m2\n" +
                            "Largura: " + edt_txt_larg_amb.getText().toString() + " m2\n" +
                            "Altura: " + edt_txt_alt_amb.getText().toString() + " m2\n" +
                            "Area Total: " + area + "m2\n");
                    nota.append("-----------\n");
                    nota.append("Total de Materiais\n");
                    nota.append(material.getNome() + "\n");
                    nota.append((comp_amb/ (material.getComprimento()/100)) * (alt_amb/(material.getAltura()/100)) + " un");
                    nota.append("Total de Materiais\n");
                    Intent it = new Intent(Tijolo.this, Resultado.class);
                    it.putExtra("MATERIAL", material);
                    it.putExtra("NOTA", (Serializable) nota);
                    it.putExtra("COMP_AMB", (double) comp_amb);
                    it.putExtra("LARG_AMB", (double) larg_amb);
                    it.putExtra("ALT_AMB", (double) alt_amb);
                    it.putExtra("AREA_TOTAL", (double) area);
                    startActivity(it);

                    /*TextView tv_n_mat = new TextView(Tijolo.this);
                    TextView tv_dim_mat = new TextView(Tijolo.this);
                    TextView tv_desc_mat = new TextView(Tijolo.this);
                    TextView tv_qtde_mat = new TextView(Tijolo.this);
                    TextView tv_un_mat = new TextView(Tijolo.this);

                    LinearLayout ll_material = new LinearLayout(Tijolo.this);
                    TextView titulo_ll_mat = new TextView(Tijolo.this);
                    titulo_ll_mat.setTextSize(14);
                    titulo_ll_mat.setTextColor(Color.WHITE);
                    titulo_ll_mat.setBackground(Drawable.createFromPath("#000000"));
                    titulo_ll_mat.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    ll_material.addView(titulo_ll_mat);

                    LinearLayout ll_dados = new LinearLayout(Tijolo.this);

                    tv_n_mat.setText(material.getNome());
                    tv_n_mat.setTextSize(14);
                    tv_n_mat.setTextColor(android.R.color.white);
                    tv_n_mat.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    ll_material.addView(tv_n_mat);

                    tv_dim_mat.setText("("+material.getLargura()+" cm"+material.getComprimento()+" cm"+material.getAltura()+" cm"+")");
                    tv_dim_mat.setTextSize(14);
                    //tv_dim_mat.setTextColor(0x0);
                    tv_dim_mat.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    ll_material.addView(tv_dim_mat);

                    tv_desc_mat.setText("Tijolo");
                    tv_desc_mat.setTextSize(14);
                    //tv_desc_mat.setTextColor(0x0);
                    tv_desc_mat.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    ll_dados.addView(tv_desc_mat);

                    tv_qtde_mat.setText(""+((comp_amb / (material.getComprimento()/100)) * (alt_amb/(material.getAltura()/100))));
                    tv_qtde_mat.setTextSize(14);
                    //tv_qtde_mat.setTextColor(0x0);
                    tv_qtde_mat.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    ll_dados.addView(tv_qtde_mat);

                    tv_un_mat.setText("un");
                    tv_un_mat.setTextSize(14);
                    //tv_un_mat.setTextColor(0x0);
                    tv_un_mat.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    ll_dados.addView(tv_un_mat);

                    ll.addView(ll_material);
                    ll.addView(ll_dados);*/
                }catch (Exception ex){
                    AlertDialog.Builder msg = new AlertDialog.Builder(Tijolo.this);
                    msg.setMessage(ex.getMessage());
                    msg.setNeutralButton("OK", null);
                    msg.show();
                }
            }
        });
    }
}
