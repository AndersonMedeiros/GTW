package com.example.andersonmedeiros.gtow;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.andersonmedeiros.gtow.modelo.Material;

import java.text.DecimalFormat;

public class Resultado extends AppCompatActivity {

    Material material;

    private LinearLayout ll_material;
    private LinearLayout ll_dados_gerais;
    private LinearLayout ll_dados;

    @SuppressLint({"ResourceType", "NewApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        ll_material = (LinearLayout) findViewById(R.id.ll_material);
        ll_dados_gerais = (LinearLayout) findViewById(R.id.ll_dados_gerais);
        ll_dados = (LinearLayout) findViewById(R.id.ll_dados);

        /*
        Bundle bundle = getIntent().getExtras();
        if ((bundle != null) && bundle.containsKey("MATERIAL") && bundle.containsKey("NOTA")) {
            material = (Material) bundle.getSerializable("MATERIAL");
            String nota = (String) bundle.getSerializable("NOTA");
            TextView tv_mat = new TextView(this);

            String mat = material.getNome() + ": " + material.getLargura() + "x" + material.getComprimento() + "x" + material.getAltura();
            tv_mat.setId();
            tv_mat.setText(mat + nota);
            tv_mat.setTextSize(14);
            tv_mat.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            ll_material.addView(tv_mat);


        }

        if ((bundle != null) && bundle.containsKey("COMP_AMB") && bundle.containsKey("LARG_AMB") && bundle.containsKey("ALT_AMB")
                && bundle.containsKey("AREA_TOTAL")) {
            LinearLayout linha = new LinearLayout(this);
            linha.setId(1);
            linha.setOrientation(LinearLayout.HORIZONTAL);
            TextView tv_desc = new TextView(this);
            tv_desc.setId(2);
            tv_desc.setText("Area Total");
            tv_desc.setTextSize(14);
            tv_desc.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            linha.addView(tv_desc);

            TextView tv_qtde = new TextView(this);
            tv_qtde.setId(3);
            tv_qtde.setText(bundle.getString("AREA_TOTAL"));
            tv_qtde.setTextSize(14);
            tv_qtde.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            linha.addView(tv_qtde);

            TextView tv_un = new TextView(this);
            tv_un.setId(4);
            tv_un.setText("m2");
            tv_un.setTextSize(14);
            tv_un.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            linha.addView(tv_un);
            /*
            TextView tv_comp =  new TextView(this);
            tv_comp.setId(2);
            tv_comp.setText("Comprimento");
            tv_comp.setTextSize(14);
            tv_comp.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            linha.addView(tv_comp);

            TextView tv_larg =  new TextView(this);
            tv_larg.setId(2);
            tv_larg.setText("Largura");
            tv_larg.setTextSize(14);
            tv_larg.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            linha.addView(tv_larg);

            TextView tv_alt =  new TextView(this);
            tv_alt.setId(2);
            tv_alt.setText("Altura");
            tv_alt.setTextSize(14);
            tv_alt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            linha.addView(tv_alt);

        }*/

        try{
        Bundle bundle = getIntent().getExtras();
        if ((bundle != null) && bundle.containsKey("MATERIAL") && bundle.containsKey("NOTA")
                && bundle.containsKey("COMP_AMB") && bundle.containsKey("LARG_AMB")
                && bundle.containsKey("ALT_AMB") && bundle.containsKey("AREA_TOTAL")) {
            DecimalFormat df = new DecimalFormat("0.0");
            TextView tv_n_mat = new TextView(this);
            TextView tv_dim_mat = new TextView(this);
            TextView tv_desc_mat = new TextView(this);
            TextView tv_qtde_mat = new TextView(this);
            TextView tv_un_mat = new TextView(this);

            LinearLayout ll_d1 = new LinearLayout(this);
            ll_d1.setOrientation(LinearLayout.HORIZONTAL);
            ll_d1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

            material = (Material) bundle.getSerializable("MATERIAL");
            double comp_amb = (double) bundle.getDouble("COMP_AMB");
            double larg_amb = (double) bundle.getDouble("LARG_AMB");
            double alt_amb = (double) bundle.getDouble("ALT_AMB");
            double area_total = (double) bundle.getDouble("AREA_TOTAL");

            tv_n_mat.setText(material.getNome());
            tv_n_mat.setTextSize(14);
            tv_n_mat.setTextColor(Color.BLACK);
            tv_n_mat.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            ll_material.addView(tv_n_mat);

            tv_dim_mat.setText("("+material.getLargura()+" cm"+material.getComprimento()+" cm"+material.getAltura()+" cm"+")");
            tv_dim_mat.setTextSize(14);
            tv_dim_mat.setTextColor(Color.BLACK);
            tv_dim_mat.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            ll_material.addView(tv_dim_mat);

            tv_desc_mat.setText("Tijolo");
            tv_desc_mat.setTextSize(14);
            tv_desc_mat.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            tv_desc_mat.setTextColor(Color.BLACK);
            tv_desc_mat.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            ll_d1.addView(tv_desc_mat);

            tv_qtde_mat.setText(""+df.format((comp_amb / (material.getComprimento()/100)) * (alt_amb/(material.getAltura()/100))));
            tv_qtde_mat.setTextSize(14);
            tv_qtde_mat.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            tv_qtde_mat.setTextColor(Color.BLACK);
            tv_qtde_mat.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            ll_d1.addView(tv_qtde_mat);

            tv_un_mat.setText("un");
            tv_un_mat.setTextSize(14);
            tv_un_mat.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            tv_un_mat.setTextColor(Color.BLACK);
            tv_un_mat.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            ll_d1.addView(tv_un_mat);

            ll_dados_gerais.addView(ll_d1);
            /*tv_desc_mat.setText("Cimento");
            tv_desc_mat.setTextSize(14);
            //tv_desc_mat.setTextColor(0x0);
            tv_desc_mat.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            ll_dados_gerais.addView(tv_desc_mat);*/

        }}catch (Exception ex){
            AlertDialog.Builder msg = new AlertDialog.Builder(Resultado.this);
            msg.setMessage(ex.getMessage());
            msg.setNeutralButton("OK", null);
            msg.show();
        }


    }
}


