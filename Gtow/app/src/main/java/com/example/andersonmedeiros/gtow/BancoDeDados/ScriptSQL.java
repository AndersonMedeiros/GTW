package com.example.andersonmedeiros.gtow.BancoDeDados;

/**
 * Created by ANDERSON MEDEIROS on 14/11/2017.
 */

public class ScriptSQL {

    public static String createUsuario(){

        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS USUARIO( ");
        sql.append("CPF VARCHAR(11) NOT NULL PRIMARY KEY, ");
        sql.append("NOME VARCHAR(50) NOT NULL, ");
        sql.append("EMAIL VARCHAR(50) NOT NULL, ");
        sql.append("FONE VARCHAR(11) NOT NULL, ");
        sql.append("SENHA VARCHAR(20) NOT NULL );");

        return sql.toString();
    }

    public static String createMaterial(){

        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS MATERIAL( ");
        sql.append("ID VARCHAR(8) NOT NULL PRIMARY KEY, ");
        sql.append("NOME VARCHAR(50) NOT NULL, ");
        sql.append("CATEGORIA VARCHAR(50) NOT NULL, ");
        sql.append("LARGURA DOUBLE NULL, ");
        sql.append("COMPRIMENTO DOUBLE NULL, ");
        sql.append("ALTURA DOUBLE NULL, ");
        sql.append("ESPESSURA DOUBLE NULL, ");
        sql.append("IC INT NULL );");

        return sql.toString();
    }
}
