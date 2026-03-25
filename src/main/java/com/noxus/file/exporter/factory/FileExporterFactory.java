package com.noxus.file.exporter.factory;

import com.noxus.exception.BadRequestException;
import com.noxus.file.exporter.contract.FileExporter;
import com.noxus.file.exporter.impl.CsvExporter;
import com.noxus.file.exporter.impl.XlsxExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class FileExporterFactory {

    private Logger logger = LoggerFactory.getLogger(FileExporterFactory.class);

    @Autowired
    private ApplicationContext context;

    public FileExporter getExporter(String fileName) throws Exception {
        if (fileName.endsWith(".xlsx")) {
            return context.getBean(XlsxExporter.class);
        } else if (fileName.endsWith(".csv")) {
            return context.getBean(CsvExporter.class);
        } else {
            throw new BadRequestException("Invalid file format");
        }
    }
}
