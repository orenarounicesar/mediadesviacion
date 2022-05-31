package com.orenarounicesar.pojos;

public class Parte {

    private String name;
    private int numberItems;
    private int partSize;
    private int totalSize;
    private int contadorParte;
    
    public Parte() {
    }

    public Parte(String name, int numberItems, int partSize, int totalSize, int contadorParte) {
        this.name = name;
        this.numberItems = numberItems;
        this.partSize = partSize;
        this.totalSize = totalSize;
        this.contadorParte = contadorParte;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberItems() {
        return numberItems;
    }

    public void setNumberItems(int numberItems) {
        this.numberItems = numberItems;
    }

    public int getPartSize() {
        return partSize;
    }

    public void setPartSize(int partSize) {
        this.partSize = partSize;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getContadorParte() {
        return contadorParte;
    }

    public void setContadorParte(int contadorParte) {
        this.contadorParte = contadorParte;
    }

    @Override
    public String toString() {
        return "Parte [contadorParte=" + contadorParte + ", name=" + name + ", numberItems=" + numberItems
                + ", partSize=" + partSize + ", totalSize=" + totalSize + "]";
    }

}
