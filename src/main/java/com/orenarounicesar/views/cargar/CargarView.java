package com.orenarounicesar.views.cargar;

import java.io.InputStream;

import com.orenarounicesar.businesslogic.SeveralProcesses;
import com.orenarounicesar.pojos.Listas;
import com.orenarounicesar.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Cargar")
@Route(value = "cargar", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class CargarView extends VerticalLayout {

    private TextArea txaInfo;
    private Upload upload;
    private Listas listas;


    public CargarView() {

        MemoryBuffer memoryBuffer = new MemoryBuffer();
        upload = new Upload(memoryBuffer);
        upload.addSucceededListener(event -> {
            // Get information about the uploaded file
            InputStream fileData = memoryBuffer.getInputStream();
            // String fileName = event.getFileName();
            // long contentLength = event.getContentLength();
            // String mimeType = event.getMIMEType();
            listas = SeveralProcesses.getListas(fileData);
            txaInfo.setValue("Valores Cargados:\n" + listas.toString());

            txaInfo.setValue(txaInfo.getValue() + "\n\nMedia:"
                + "\nLista1: " + SeveralProcesses.calcularMedia(listas.getLista1())
                + "\nLista2: " + SeveralProcesses.calcularMedia(listas.getLista2())
                + "\n\nDesviación Estandar:"
                + "\nLista1: " + SeveralProcesses.calcularDesviacionEstandar(listas.getLista1())
                + "\nLista2: " + SeveralProcesses.calcularDesviacionEstandar(listas.getLista2())
                );
        
            // Do something with the file data
            // processFile(fileData, fileName, contentLength, mimeType);
        });
        

        
        txaInfo = new TextArea();
        txaInfo.setWidth("100%");


        setMargin(true);
        setHorizontalComponentAlignment(Alignment.START, upload, txaInfo);
        setWidth("100%");

        add(upload, txaInfo);
    }

}
