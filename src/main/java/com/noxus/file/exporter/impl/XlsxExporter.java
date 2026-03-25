package com.noxus.file.exporter.impl;

import com.noxus.data.dto.PersonDTO;
import com.noxus.file.exporter.contract.FileExporter;
import org.springframework.core.io.Resource;

import java.util.List;

public class XlsxExporter implements FileExporter {
    @Override
    public Resource exportFile(List<PersonDTO> people) throws Exception {
        return null;
    }
}
