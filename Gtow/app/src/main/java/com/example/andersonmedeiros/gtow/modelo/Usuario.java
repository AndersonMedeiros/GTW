package com.example.andersonmedeiros.gtow.modelo;

import java.io.Serializable;

/**
 * Created by ANDERSON MEDEIROS on 14/11/2017.
 */

public class Usuario implements Serializable{

    public static String CPF="cpf";
    public static String NOME="nome";
    public static String EMAIL="email";
    public static String FONE="fone";
    public static String SENHA="senha";

    private String cpf;
    private String nome;
    private String email;
    private String fone;
    private String senha;

    public Usuario() {
    }

    public Usuario(String cpf, String nome, String email, String fone, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.fone = fone;
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone() { return fone; }

    public void setFone(String fone)  { this.fone = fone; }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", fone='" + fone + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
