package com.example.andersonmedeiros.gtow;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Tinta extends AppCompatActivity {

    private EditText edt_txt_alt_amb;
    private EditText edt_txt_larg_amb;
    private EditText edt_txt_larg_porta;
    private EditText edt_txt_alt_porta;
    private EditText edt_txt_alt_janela;
    private EditText edt_txt_larg_janela;
    private EditText edt_txt_qtde_demaos;
    private EditText edt_txt_qtde_porta;
    private EditText edt_txt_qtde_janela;
    private EditText edt_txt_rend_lata;

    private Button bt_calcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        edt_txt_alt_amb = (EditText)findViewById(R.id.edt_txt_alt_amb);
        edt_txt_larg_amb = (EditText)findViewById(R.id.edt_txt_larg_amb);
        edt_txt_larg_porta = (EditText)findViewById(R.id.edt_txt_larg_porta);
        edt_txt_alt_porta = (EditText)findViewById(R.id.edt_txt_alt_porta);
        edt_txt_alt_janela = (EditText)findViewById(R.id.edt_txt_alt_janela);
        edt_txt_larg_janela = (EditText)findViewById(R.id.edt_txt_larg_janela);
        edt_txt_qtde_demaos = (EditText)findViewById(R.id.edt_txt_qtde_demao);
        edt_txt_qtde_porta = (EditText)findViewById(R.id.edt_txt_qtde_porta);
        edt_txt_qtde_janela = (EditText)findViewById(R.id.edt_txt_qtde_janela);
        edt_txt_rend_lata = (EditText)findViewById(R.id.edt_txt_rend_lata);

        bt_calcular = (Button)findViewById(R.id.bt_calcular);

        bt_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Tinta.this, ResultTinta.class);
                it.putExtra("ALT_AMB", Double.parseDouble(edt_txt_alt_amb.getText().toString()));
                it.putExtra("LARG_AMB", Double.parseDouble(edt_txt_larg_amb.getText().toString()));
                it.putExtra("ALT_PORTA", Double.parseDouble(edt_txt_alt_porta.getText().toString()));
                it.putExtra("LARG_PORTA", Double.parseDouble(edt_txt_larg_porta.getText().toString()));
                it.putExtra("ALT_JANELA", Double.parseDouble(edt_txt_alt_janela.getText().toString()));
                it.putExtra("LARG_JANELA", Double.parseDouble(edt_txt_larg_janela.getText().toString()));
                it.putExtra("QTDE_DEMAOS", Double.parseDouble(edt_txt_qtde_demaos.getText().toString()));
                it.putExtra("QTDE_PORTA", Double.parseDouble(edt_txt_qtde_porta.getText().toString()));
                it.putExtra("QTDE_JANELA", Double.parseDouble(edt_txt_qtde_janela.getText().toString()));
                it.putExtra("REND_LATA", Double.parseDouble(edt_txt_rend_lata.getText().toString()));
                startActivity(it);
            }
        });
    }

}
