package com.example.andersonmedeiros.gtow.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

/**
 * Created by ANDERSON MEDEIROS on 14/11/2017.
 */

public class UsuarioDao {

    private SQLiteDatabase conn;

    public UsuarioDao(SQLiteDatabase conn) {
        this.conn = conn;
    }

    public ContentValues preencherValores(Usuario usuario){
        ContentValues valores = new ContentValues();
        valores.put("CPF", usuario.getCpf());
        valores.put("NOME", usuario.getNome());
        valores.put("EMAIL", usuario.getEmail());
        valores.put("FONE", usuario.getFone());
        valores.put("SENHA", usuario.getSenha());
        return valores;
    }

    public void inserir(Usuario usuario){
        ContentValues valores = preencherValores(usuario);
        conn.insertOrThrow("USUARIO", null, valores);
    }

    public void alterar(Usuario usuario){
        ContentValues valores = preencherValores(usuario);
        conn.update("USUARIO", valores, "CPF = ?", new String[] {String.valueOf(usuario.getCpf())});
    }

    public void excluir(String cpf){
        conn.delete("USUARIO", "CPF = ?", new String[]{String.valueOf(cpf)});
    }

    public ArrayAdapter<Usuario> pesqUsuarios(Context context){
        ArrayAdapter<Usuario> adpUsuarios =  new ArrayAdapter<Usuario>(context, android.R.layout.simple_list_item_1);
        Cursor cursor = conn.query("USUARIO", null,null,null,null,null,null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                Usuario usuario = new Usuario();

                usuario.setCpf(cursor.getString(0));
                usuario.setNome(cursor.getString(1));
                usuario.setEmail(cursor.getString(2));
                usuario.setFone(cursor.getString(3));
                usuario.setSenha(cursor.getString(4));

                adpUsuarios.add(usuario);
            }while (cursor.moveToNext());

        }
        return adpUsuarios;
    }

    public Usuario validacaoLogin(String cpf, String senha){
        Usuario usuario = null;
        Cursor cursor = conn.query("USUARIO", null,null,null,null,null,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                if (cursor.getString(0).equals(cpf) && cursor.getString(4).equals(senha)) {
                    usuario = new Usuario();

                    usuario.setCpf(cursor.getString(0));
                    usuario.setNome(cursor.getString(1));
                    usuario.setEmail(cursor.getString(2));
                    usuario.setFone(cursor.getString(3));
                    usuario.setSenha(cursor.getString(4));
                }

            }while (cursor.moveToNext());

        }
        return usuario;
    }
}
