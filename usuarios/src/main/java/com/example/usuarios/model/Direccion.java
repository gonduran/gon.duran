package com.example.usuarios.model;

public class Direccion {

    private int idDireccion;
    private String calle;
    private String numero;
    private String comuna;
    private String ciudad;
    private String region;
    private String tipoDireccion;
    private boolean paraDespacho;
    private boolean paraFacturacion;

    public Direccion(int idDireccion, String calle, String numero, String comuna, String ciudad,
                    String region, String tipoDireccion, boolean paraDespacho, boolean paraFacturacion) {
        this.idDireccion = idDireccion;
        this.calle = calle;
        this.numero = numero;
        this.comuna = comuna;
        this.ciudad = ciudad;
        this.region = region;
        this.tipoDireccion = tipoDireccion;
        this.paraDespacho = paraDespacho;
        this.paraFacturacion = paraFacturacion;
    }

    // Getters y setters

    public int getIdDireccion() {
        return idDireccion;
    }

    public String getCalle() {
        return calle;
    }

    public String getNumero() {
        return numero;
    }

    public String getComuna() {
        return comuna;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getRegion() {
        return region;
    }

    public String getTipoDireccion() {
        return tipoDireccion;
    }

    public boolean getParaDespacho() {
        return paraDespacho;
    }

    public boolean getParaFacturacion() {
        return paraFacturacion;
    }

}
