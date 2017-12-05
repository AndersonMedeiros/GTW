package com.example.andersonmedeiros.gtow;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ResultTinta extends AppCompatActivity {
    private double alt_amb, larg_amb, alt_porta, larg_porta, alt_janela, larg_janela, qtde_demaos, qtde_porta, qtde_janela, rend_lata;
    private LinearLayout ll_material;
    private LinearLayout ll_dados_gerais;
    private LinearLayout ll_total_materiais;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_tinta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        ll_material = (LinearLayout) findViewById(R.id.ll_material);
        ll_dados_gerais = (LinearLayout) findViewById(R.id.ll_dados_gerais);
        ll_total_materiais = (LinearLayout) findViewById(R.id.ll_total_materiais);

        Bundle bundle = getIntent().getExtras();
        try {
            if ((bundle != null) && bundle.containsKey("ALT_AMB") && bundle.containsKey("LARG_AMB")
                    && bundle.containsKey("ALT_PORTA") && bundle.containsKey("LARG_PORTA") && bundle.containsKey("ALT_JANELA")
                    && bundle.containsKey("LARG_JANELA") && bundle.containsKey("QTDE_DEMAOS") && bundle.containsKey("QTDE_PORTA")
                    && bundle.containsKey("QTDE_JANELA")) {

                DecimalFormat df = new DecimalFormat("0.0");

                alt_amb = bundle.getDouble("ALT_AMB");
                larg_amb = bundle.getDouble("LARG_AMB");
                alt_porta = bundle.getDouble("ALT_PORTA");
                alt_janela = bundle.getDouble("ALT_JANELA");
                larg_porta = bundle.getDouble("LARG_PORTA");
                larg_janela = bundle.getDouble("LARG_JANELA");
                qtde_demaos = bundle.getDouble("QTDE_DEMAOS");
                qtde_janela = bundle.getDouble("QTDE_JANELA");
                qtde_porta = bundle.getDouble("QTDE_PORTA");
                rend_lata = bundle.getDouble("REND_LATA");

                //DADOS GERAIS
                TextView tv_desc_area = new TextView(this);
                TextView tv_area_total = new TextView(this);
                TextView tv_un_area = new TextView(this);
                TextView tv_desc_ap = new TextView(this);
                TextView tv_ap = new TextView(this);
                TextView tv_un_ap = new TextView(this);
                TextView tv_desc_demaos = new TextView(this);
                TextView tv_qtde_demaos = new TextView(this);
                TextView tv_un_demaos = new TextView(this);

                LinearLayout ll_at = new LinearLayout(this);
                ll_at.setOrientation(LinearLayout.HORIZONTAL);
                ll_at.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                tv_desc_area.setText("Área Total");
                tv_desc_area.setTextSize(14);
                tv_desc_area.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                tv_desc_area.setTextColor(Color.BLACK);
                tv_desc_area.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                ll_at.addView(tv_desc_area);

                tv_area_total.setText("" + df.format(larg_amb * alt_amb));
                tv_area_total.setTextSize(14);
                tv_area_total.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                tv_area_total.setTextColor(Color.BLACK);
                tv_area_total.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                ll_at.addView(tv_area_total);

                tv_un_area.setText("m2");
                tv_un_area.setTextSize(14);
                tv_un_area.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                tv_un_area.setTextColor(Color.BLACK);
                tv_un_area.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                ll_at.addView(tv_un_area);
                ll_dados_gerais.addView(ll_at);

                LinearLayout ll_ap = new LinearLayout(this);
                ll_ap.setOrientation(LinearLayout.HORIZONTAL);
                ll_ap.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                double ap = (larg_amb*alt_amb) - ((qtde_porta*larg_porta*alt_porta) + (qtde_janela*larg_janela*alt_janela));

                tv_desc_ap.setText("Área a ser pintada");
                tv_desc_ap.setTextSize(14);
                tv_desc_ap.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                tv_desc_ap.setTextColor(Color.BLACK);
                tv_desc_ap.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                ll_ap.addView(tv_desc_ap);

                tv_ap.setText("" + df.format(ap));
                tv_ap.setTextSize(14);
                tv_ap.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                tv_ap.setTextColor(Color.BLACK);
                tv_ap.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                ll_ap.addView(tv_ap);

                tv_un_ap.setText("m2");
                tv_un_ap.setTextSize(14);
                tv_un_ap.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                tv_un_ap.setTextColor(Color.BLACK);
                tv_un_ap.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                ll_ap.addView(tv_un_ap);
                ll_dados_gerais.addView(ll_ap);

                LinearLayout ll_qd = new LinearLayout(this);
                ll_qd.setOrientation(LinearLayout.HORIZONTAL);
                ll_qd.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                tv_desc_demaos.setText("Quanitdade de Demãos");
                tv_desc_demaos.setTextSize(14);
                tv_desc_demaos.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                tv_qtde_demaos.setTextColor(Color.BLACK);
                tv_qtde_demaos.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                ll_qd.addView(tv_desc_demaos);

                tv_qtde_demaos.setText("" + df.format(qtde_demaos));
                tv_qtde_demaos.setTextSize(14);
                tv_qtde_demaos.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                tv_qtde_demaos.setTextColor(Color.BLACK);
                tv_qtde_demaos.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                ll_qd.addView(tv_qtde_demaos);

                tv_un_demaos.setText("qtde");
                tv_un_demaos.setTextSize(14);
                tv_un_demaos.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                tv_un_demaos.setTextColor(Color.BLACK);
                tv_un_demaos.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                ll_qd.addView(tv_un_demaos);
                ll_dados_gerais.addView(ll_qd);

                //TOTAL DE MATERIAIS
                TextView tv_desc_tin = new TextView(this);
                TextView tv_qtde_tin = new TextView(this);
                TextView tv_un_tin = new TextView(this);

                LinearLayout ll_qt = new LinearLayout(this);
                ll_qt.setOrientation(LinearLayout.HORIZONTAL);
                ll_qt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                tv_desc_tin.setText("Volume de Tinta");
                tv_desc_tin.setTextSize(14);
                tv_desc_tin.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                tv_desc_tin.setTextColor(Color.BLACK);
                tv_desc_tin.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                ll_qt.addView(tv_desc_tin);

                double v;
                if (ap==0)
                    v = ((larg_amb*alt_amb)*qtde_demaos)/rend_lata;
                else
                    v = (ap*qtde_demaos)/rend_lata;

                tv_qtde_tin.setText("" + df.format(v));
                tv_qtde_tin.setTextSize(14);
                tv_qtde_tin.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                tv_qtde_tin.setTextColor(Color.BLACK);
                tv_qtde_tin.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                ll_qt.addView(tv_qtde_tin);


                tv_un_tin.setText("Litros");
                tv_un_tin.setTextSize(14);
                tv_un_tin.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                tv_un_tin.setTextColor(Color.BLACK);
                tv_un_tin.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                ll_qt.addView(tv_un_tin);
                ll_total_materiais.addView(ll_qt);

            }
        }catch (Exception ex){
            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setMessage(alt_amb + larg_janela + larg_amb + "\n"+ex.getMessage());
            msg.setNeutralButton("OK", null);
            msg.show();
        }
    }

}
