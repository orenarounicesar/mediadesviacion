package com.orenarounicesar.views.about;

import java.io.InputStream;

import com.orenarounicesar.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Contador")
@Route(value = "contador", layout = MainLayout.class)
public class ContadorView extends VerticalLayout {

    private Upload upload;

    public ContadorView() {
        
        MemoryBuffer memoryBuffer = new MemoryBuffer();
        upload = new Upload(memoryBuffer);
        upload.addSucceededListener(event -> {
            InputStream fileData = memoryBuffer.getInputStream();
            
        });

        setMargin(true);
        setHorizontalComponentAlignment(Alignment.START, upload);
        setWidth("100%");

        add(upload);
    }

}
