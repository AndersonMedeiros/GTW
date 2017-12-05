package com.example.andersonmedeiros.gtow.BancoDeDados;

/**
 * Created by ANDERSON MEDEIROS on 14/11/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDados extends SQLiteOpenHelper{
    public BancoDados(Context context) {
        super(context, "GTOW", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptSQL.createUsuario());
        db.execSQL(ScriptSQL.createMaterial());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
