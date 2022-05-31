package com.orenarounicesar.businesslogic;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.orenarounicesar.pojos.Listas;
import com.orenarounicesar.pojos.Parte;

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

    public static void incrementarContadoresLineas(List<Parte> listaPartes, int contadorPartes) {
        for ( Parte parte : listaPartes ) {
            if ( parte.getContadorParte() == 1 || parte.getContadorParte() == contadorPartes )
                listaPartes.set(
                    listaPartes.indexOf( parte ), 
                    new Parte(parte.getName(), parte.getNumberItems(), parte.getPartSize()+1, parte.getTotalSize(), parte.getContadorParte())
                );
        }
        // System.out.println("\n");
    }

    public static void addPart(List<Parte> listaPartes, String nombreParte, int contadorParte) {

        Parte parteActulizar = null;

        listaPartes.add( new Parte(nombreParte, 0, 0, 0, contadorParte) );


        for ( Parte parte : listaPartes ) {
            if ( parte.getContadorParte() == contadorParte - 1 ) {
                parteActulizar = parte;
                break;
            }
        }

        if ( parteActulizar != null ) {
            listaPartes.set(
                listaPartes.indexOf(parteActulizar),
                new Parte(parteActulizar.getName(), parteActulizar.getNumberItems() + 1, 0, 0, contadorParte)
            );
        }

    }

    public List<Parte> getTableParts() {
        return null;
    }

    
    public void test() {
        
    }

    public static List<Parte> getLines(InputStream fis) {
        List<Parte> listaPartes = new ArrayList<>();
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        
        br.lines().forEach(new Consumer<String>() {
            int contadorPartes = 0;
            int contadorLlaves = 0;
            @Override
            public void accept(String line) {
                if ( line.contains("{") && line.contains(" class ") && !line.contains("\"") ) {
                    contadorPartes++;
                    addPart(listaPartes, "CLASS", contadorPartes);
                } else if ( line.contains("{") && contadorLlaves == 1 
                    && line.contains("static ") 
                    && line.contains("(") 
                    && line.contains(")") 
                    && !line.contains("for") 
                    && !line.contains("if") 
                    && !line.contains("while") 
                    && contadorLlaves == 1 ) {
                    contadorPartes++;
                    addPart(listaPartes, "STATIC METHOD", contadorPartes);
                } else if ( line.contains("{") && contadorLlaves == 1 
                    && !line.contains("static ") 
                    && line.contains("(") 
                    && line.contains(")") 
                    && !line.contains("for") 
                    && !line.contains("if") 
                    && !line.contains("while") 
                    && contadorLlaves == 1 ) {
                    contadorPartes++;
                    addPart(listaPartes, "METHOD", contadorPartes);
                } 

                if ( line.contains("{") ) {
                    contadorLlaves++;
                } 

                if ( line.contains("}") ) {
                    contadorLlaves--;
                }

                if ( contadorPartes > 0 ) {
                    if ( !line.trim().isEmpty() )
                        incrementarContadoresLineas(listaPartes, contadorPartes);
                }

            }
        });
        int sumaTotal = 0;
        for ( Parte parte : listaPartes ) {
            if ( parte.getContadorParte() == 1 )
                sumaTotal = parte.getPartSize();
        };
        listaPartes.add(new Parte("Total", 0, 0, sumaTotal, listaPartes.size() + 1));

        return listaPartes;
    }
    
}
