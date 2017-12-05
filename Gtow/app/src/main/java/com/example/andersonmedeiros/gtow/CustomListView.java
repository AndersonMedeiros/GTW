package com.example.andersonmedeiros.gtow;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andersonmedeiros.gtow.modelo.Material;

import java.util.List;

/**
 * Created by ANDERSON MEDEIROS on 22/11/2017.
 */

public class CustomListView extends ArrayAdapter<String> {

    private List<Material> lstMateriais;
    private Activity context;


    public CustomListView(Activity context, List<Material> lstMateriais) {
        super(context, R.layout.lst_tipos_materiais_layout);

        this.context=context;
        this.lstMateriais=lstMateriais;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        ViewHolder viewHolder = null;
        if(r==null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.lst_tipos_materiais_layout, null, true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) r.getTag();
        }
        viewHolder.ivw.setImageResource(lstMateriais.get(position).getIc());
        viewHolder.tvw1.setText(lstMateriais.get(position).getNome());
        viewHolder.tvw2.setText(lstMateriais.get(position).getLargura() + "x" + lstMateriais.get(position).getComprimento() + "x" + lstMateriais.get(position).getAltura());

        return r;
    }

    class ViewHolder{
        TextView tvw1;
        TextView tvw2;
        ImageView ivw;
        ViewHolder(View v){
            tvw1 = (TextView) v.findViewById(R.id.tv_nome_mat);
            tvw2 = (TextView) v.findViewById(R.id.tv_desc_mat);
            ivw = (ImageView) v.findViewById(R.id.ic_mat);
        }
    }
}
