package com.example.andersonmedeiros.gtow.modelo;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Created by ANDERSON MEDEIROS on 14/11/2017.
 */

public class Material implements Serializable{
    private String id;
    private String nome;
    private String categoria;
    private double largura;
    private double comprimento;
    private double altura;
    private double espessura;
    private int ic;

    public Material() {
    }

    public Material(String id, String nome, String categoria, double largura, double comprimento, double altura, double espessura, int ic) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.largura = largura;
        this.comprimento = comprimento;
        this.altura = altura;
        this.espessura = espessura;
        this.ic = ic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setId(String categoria, int proxId){
        DecimalFormat df = new DecimalFormat("0000");
        this.id = categoria+"."+df.format(proxId);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public double getComprimento() {
        return comprimento;
    }

    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
    }

    public double getAltura() { return altura; }

    public void setAltura(double altura) { this.altura = altura; }

    public double getEspessura() { return espessura; }

    public void setEspessura(double espessura) { this.espessura = espessura; }

    public int getIc() { return ic; }

    public void setIc(int ic) { this.ic = ic; }

    @Override
    public String toString() {
        return nome + "\n" + largura + "x" + comprimento + "x" + altura;
    }
}
