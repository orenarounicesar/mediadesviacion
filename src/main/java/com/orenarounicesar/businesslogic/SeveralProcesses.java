package com.orenarounicesar.businesslogic;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.orenarounicesar.pojos.Listas;

public class SeveralProcesses {
    
    public static Listas getListas(InputStream fileData) {
        try {
            ObjectMapper om = new ObjectMapper(new YAMLFactory());
        
            Listas listas = om.readValue(fileData, Listas.class);
            
            return listas;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;

    }
    
    public static float calcularMedia(List<Float> lista) {
        float valorTotal = 0;
        for (float valor : lista) {
            valorTotal += valor;
        }

        return valorTotal / lista.size();

    }
    
    public static double calcularDesviacionEstandar(List<Float> lista) {
        float media = calcularMedia(lista);
        double desviacionEstandar = 0;
        for (float valor : lista) {
            desviacionEstandar += Math.pow(valor - media, 2);
        }
        return Math.sqrt(desviacionEstandar / (lista.size() - 1));

    }
}
