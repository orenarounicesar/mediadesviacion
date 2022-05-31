package com.orenarounicesar.views.about;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import com.orenarounicesar.businesslogic.SeveralProcesses;
import com.orenarounicesar.pojos.Parte;
import com.orenarounicesar.pojos.Person;
import com.orenarounicesar.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Contador")
@Route(value = "contador", layout = MainLayout.class)
public class ContadorView extends VerticalLayout {

    private Upload upload;
    private Grid<Parte> tblResultado;

    public ContadorView() {
        
        MemoryBuffer memoryBuffer = new MemoryBuffer();
        upload = new Upload(memoryBuffer);
        upload.addSucceededListener(event -> {
            InputStream fileData = memoryBuffer.getInputStream();
            SeveralProcesses.getLines(fileData);

            List<Parte> listaPartes = SeveralProcesses.getLines(fileData);
            // for ( Parte parte : listaPartes ) {
                
            // };
            tblResultado.setItems(listaPartes);
            // tblResultado.addColumn(Parte::getName).setHeader("Part");
            // tblResultado.addColumn(Parte::getNumberItems).setHeader("Number Items");
            // tblResultado.addColumn(Parte::getPartSize).setHeader("Part Size");
            // tblResultado.addColumn(Parte::getTotalSize).setHeader("Total Size");
            // tblResultado.addColumn(Parte::getContadorParte).setHeader("Counter");
            
            tblResultado.getDataProvider().refreshAll();    
            
        });

        
        setMargin(true);
        setHorizontalComponentAlignment(Alignment.START, upload);
        setWidth("100%");
        
        add(upload);

        tblResultado = new Grid<>(Parte.class);
        tblResultado.setWidth("100%");
        add(tblResultado);
    }

}
