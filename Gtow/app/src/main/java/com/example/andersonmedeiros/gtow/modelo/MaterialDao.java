package com.example.andersonmedeiros.gtow.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANDERSON MEDEIROS on 14/11/2017.
 */

public class MaterialDao {

    private SQLiteDatabase conn;

    public MaterialDao(SQLiteDatabase conn) {
        this.conn = conn;
    }

    public void inserir(Material material){
        ContentValues valores = new ContentValues();
        valores.put("ID", material.getId());
        valores.put("NOME", material.getNome());
        valores.put("CATEGORIA", material.getCategoria());
        valores.put("LARGURA", material.getLargura());
        valores.put("COMPRIMENTO", material.getComprimento());
        valores.put("ALTURA", material.getAltura());
        valores.put("ESPESSURA", material.getEspessura());
        valores.put("IC", material.getIc());

        conn.insertOrThrow("MATERIAL", null, valores);

}
    public ArrayAdapter<Material> pesqMateriais(Context context, String categoria){
        ArrayAdapter<Material> adpMateriais =  new ArrayAdapter<Material>(context, android.R.layout.simple_list_item_1);
        Cursor cursor = conn.query("MATERIAL", null,null,null,null,null,null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                if(cursor.getString(2).equals(categoria)) {
                    Material material = new Material();

                    material.setId(cursor.getString(0));
                    material.setNome(cursor.getString(1));
                    material.setCategoria(cursor.getString(2));
                    material.setLargura(cursor.getDouble(3));
                    material.setComprimento(cursor.getDouble(4));
                    material.setAltura(cursor.getDouble(5));
                    material.setEspessura(cursor.getDouble(6));
                    material.setIc(cursor.getInt(7));

                    adpMateriais.add(material);
                }
            }while (cursor.moveToNext());

        }
        return adpMateriais;
    }

    public List<Material> materiais(){
        List<Material> lstMateriais= new ArrayList<Material>();
        Cursor cursor = conn.query("MATERIAL",null,null,null,null,null,null);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Material material = new Material();
                material.setId(cursor.getString(0));
                material.setNome(cursor.getString(1));
                material.setCategoria(cursor.getString(2));
                material.setLargura(cursor.getDouble(3));
                material.setComprimento(cursor.getDouble(4));
                material.setAltura(cursor.getDouble(5));
                material.setEspessura(cursor.getDouble(6));
                material.setIc(cursor.getInt(7));

                lstMateriais.add(material);
            }while (cursor.moveToNext());
        }

        return lstMateriais;
    }

    public String ultimoID(){
        String u = "ass";
        //String sql="SELECT ID FROM MATERIAL WHERE ID = (SELECT MAX(ID) FROM MATERIAL)";
        //conn.execSQL(sql);
        String where = "ID = (SELECT MAX(ID) FROM MATERIAL)";
        Cursor cursor = conn.query("MATERIAL", new String[]{"ID"},where,null,null,null,null);
        u = cursor.getString(0);
        return u;
    }
}