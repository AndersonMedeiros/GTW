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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.andersonmedeiros.gtow.modelo.Material;

import java.text.DecimalFormat;

public class ResultForro extends AppCompatActivity {
    Material material;

    private LinearLayout ll_material;
    private LinearLayout ll_dados_gerais;
    private LinearLayout ll_dados;

    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_forro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ll_material = (LinearLayout) findViewById(R.id.ll_material);
        ll_dados_gerais = (LinearLayout) findViewById(R.id.ll_dados_gerais);
        ll_dados = (LinearLayout) findViewById(R.id.ll_dados);

        try {
            Bundle bundle = getIntent().getExtras();

            if ((bundle != null) && bundle.containsKey("MATERIAL")
                    && bundle.containsKey("COMP_AMB") && bundle.containsKey("LARG_AMB") && bundle.containsKey("INST")) {
                material = (Material) bundle.getSerializable("MATERIAL");
                if (bundle.getString("INST").contains("Horizontal")) {
                    DecimalFormat df = new DecimalFormat("0.0");
                    int i = 0, h = 0;
                    double comp_amb = bundle.getDouble("COMP_AMB");
                    double larg_amb = bundle.getDouble("LARG_AMB");
                    while (comp_amb > 6) {
                        comp_amb -= 6;
                        i++;
                        h++;
                    }

                    TextView tv_n_mat = new TextView(this);
                    TextView tv_dim_mat = new TextView(this);
                    TextView tv_desc_mat = new TextView(this);
                    TextView tv_desc_h = new TextView(this);
                    TextView tv_desc_rd = new TextView(this);
                    TextView tv_desc_mad = new TextView(this);
                    TextView tv_qtde_mat = new TextView(this);
                    TextView tv_qtde_h = new TextView(this);
                    TextView tv_qtde_rd = new TextView(this);
                    TextView tv_qtde_mad = new TextView(this);
                    TextView tv_un_mat = new TextView(this);
                    TextView tv_un_h = new TextView(this);
                    TextView tv_un_rd = new TextView(this);
                    TextView tv_un_mad = new TextView(this);


                    tv_n_mat.setText(material.getNome());
                    tv_n_mat.setTextSize(14);
                    tv_n_mat.setTextColor(Color.BLACK);
                    tv_n_mat.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    ll_material.addView(tv_n_mat);

                    tv_dim_mat.setText("(" + material.getLargura() + " cm x " + material.getComprimento() + " cm" + ")");
                    tv_dim_mat.setTextSize(14);
                    tv_dim_mat.setTextColor(Color.BLACK);
                    tv_dim_mat.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    ll_material.addView(tv_dim_mat);


                    LinearLayout ll_d1 = new LinearLayout(this); ll_d1.setId(1);
                    ll_d1.setOrientation(LinearLayout.HORIZONTAL);
                    ll_d1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                    tv_desc_mat.setText("Forro PVC");
                    tv_desc_mat.setTextSize(14);
                    tv_desc_mat.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                    tv_desc_mat.setTextColor(Color.BLACK);
                    tv_desc_mat.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    ll_d1.addView(tv_desc_mat);

                    tv_qtde_mat.setText("" + df.format(larg_amb / material.getLargura()));
                    tv_qtde_mat.setTextSize(14);
                    tv_qtde_mat.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                    tv_qtde_mat.setTextColor(Color.BLACK);
                    tv_qtde_mat.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    ll_d1.addView(tv_qtde_mat);

                    tv_un_mat.setText("Peças de " + comp_amb + " m2");
                    tv_un_mat.setTextSize(14);
                    tv_un_mat.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                    tv_un_mat.setTextColor(Color.BLACK);
                    tv_un_mat.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    ll_d1.addView(tv_un_mat);
                    ll_dados_gerais.addView(ll_d1);

                    LinearLayout ll_h = new LinearLayout(this); ll_h.setId(3);
                    ll_h.setOrientation(LinearLayout.HORIZONTAL);
                    ll_h.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                    tv_desc_h.setText("H");
                    tv_desc_h.setTextSize(14);
                    tv_desc_h.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                    tv_desc_h.setTextColor(Color.BLACK);
                    tv_desc_h.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    ll_h.addView(tv_desc_h);

                    tv_qtde_h.setText(""+df.format(h));
                    tv_qtde_h.setTextSize(14);
                    tv_qtde_h.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                    tv_qtde_h.setTextColor(Color.BLACK);
                    tv_qtde_h.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    ll_h.addView(tv_qtde_h);

                    tv_un_h.setText("Peças de 6.0 m");
                    tv_un_h.setTextSize(14);
                    tv_un_h.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                    tv_un_h.setTextColor(Color.BLACK);
                    tv_un_h.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    ll_h.addView(tv_un_h);


                    ll_dados_gerais.addView(ll_h);

                    LinearLayout ll_rd = new LinearLayout(this); ll_h.setId(4);
                    ll_rd.setOrientation(LinearLayout.HORIZONTAL);
                    ll_rd.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                    tv_desc_rd.setText("Roda Forro");
                    tv_desc_rd.setTextSize(14);
                    tv_desc_rd.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                    tv_desc_rd.setTextColor(Color.BLACK);
                    tv_desc_rd.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    ll_rd.addView(tv_desc_rd);
                    double rd = ((larg_amb*2)+(comp_amb*2))/6.0;

                    tv_qtde_rd.setText(""+df.format(rd));
                    tv_qtde_rd.setTextSize(14);
                    tv_qtde_rd.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                    tv_qtde_rd.setTextColor(Color.BLACK);
                    tv_qtde_rd.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    ll_rd.addView(tv_qtde_rd);

                    tv_un_rd.setText("Peças de 6.0 m");
                    tv_un_rd.setTextSize(14);
                    tv_un_rd.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                    tv_un_rd.setTextColor(Color.BLACK);
                    tv_un_rd.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    ll_rd.addView(tv_un_rd);


                    ll_dados_gerais.addView(ll_rd);

                    LinearLayout ll_mad = new LinearLayout(this);
                    ll_mad.setOrientation(LinearLayout.HORIZONTAL);
                    ll_mad.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                    tv_desc_mad.setText("Madeira");
                    tv_desc_mad.setTextSize(14);
                    tv_desc_mad.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                    tv_desc_mad.setTextColor(Color.BLACK);
                    tv_desc_mad.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    ll_mad.addView(tv_desc_mad);

                    double mad = (((comp_amb/0.8)*larg_amb) + (larg_amb*2)+ (comp_amb*2))/12;

                    tv_qtde_mad.setText(""+df.format(mad));
                    tv_qtde_mad.setTextSize(14);
                    tv_qtde_mad.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                    tv_qtde_mad.setTextColor(Color.BLACK);
                    tv_qtde_mad.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    ll_mad.addView(tv_qtde_mad);

                    tv_un_mad.setText("Duzias de "+ larg_amb +"m");
                    tv_un_mad.setTextSize(14);
                    tv_un_mad.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                    tv_un_mad.setTextColor(Color.BLACK);
                    tv_un_mad.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    ll_mad.addView(tv_un_mad);


                    ll_dados_gerais.addView(ll_mad);




                }


            }
        }catch (Exception ex){
            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setMessage(ex.getMessage());
            msg.setNeutralButton("OK", null);
            msg.show();
        }
    }

}
