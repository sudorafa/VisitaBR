package com.example.orafa.visitabr.model;

import org.parceler.Parcel;

/**
 * Created by sudorafa on 05/12/17.
 */

@Parcel
public class GeoBr {
    private String codigo_ibge;
    private String nome_municipio;
    private String codigo_uf;
    private String uf;
    private String estado;
    private Double latitude;
    private Double longitude;

    public GeoBr(){

    }

    public GeoBr(String codigo_ibge, String nome_municipio, String codigo_uf, String uf, String estado, Double latitude, Double longitude) {
        this.codigo_ibge = codigo_ibge;
        this.nome_municipio = nome_municipio;
        this.codigo_uf = codigo_uf;
        this.uf = uf;
        this.estado = estado;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCodigo_ibge() {
        return codigo_ibge;
    }

    public void setCodigo_ibge(String codigo_ibge) {
        this.codigo_ibge = codigo_ibge;
    }

    public String getNome_municipio() {
        return nome_municipio;
    }

    public void setNome_municipio(String nome_municipio) {
        this.nome_municipio = nome_municipio;
    }

    public String getCodigo_uf() {
        return codigo_uf;
    }

    public void setCodigo_uf(String codigo_uf) {
        this.codigo_uf = codigo_uf;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
