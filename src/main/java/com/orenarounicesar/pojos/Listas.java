package com.orenarounicesar.pojos;

import java.util.List;

public class Listas {
    private List<Float> lista1;
    private List<Float> lista2;

    public Listas() {
    }
    
    public Listas(List<Float> lista1, List<Float> lista2) {
        this.lista1 = lista1;
        this.lista2 = lista2;
    }

    public List<Float> getLista1() {
        return lista1;
    }

    public void setLista1(List<Float> lista1) {
        this.lista1 = lista1;
    }

    public List<Float> getLista2() {
        return lista2;
    }

    public void setLista2(List<Float> lista2) {
        this.lista2 = lista2;
    }

    @Override
    public String toString() {
        return "lista1=" + lista1 + "\nlista2=" + lista2;
    }

    
}
