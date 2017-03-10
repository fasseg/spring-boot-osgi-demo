package de.isb.demo.service.impl;

import de.isb.demo.model.Visa;
import de.isb.demo.service.api.SerializationService;
import de.isb.demo.service.api.VisaService;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VisaServiceImpl implements VisaService {

    private static final Logger log = LoggerFactory.getLogger(VisaServiceImpl.class);

    private File dataDirectory;

    @Autowired
    private SerializationService serializationService;

    @Reference
    private OSGiDummyService osgiDummyService;

    @Value("${demo.application.data.directory}")
    private String dataDirectoryPath;

    @PostConstruct
    public void init() {
        this.dataDirectory = new File(dataDirectoryPath);
        if (!this.dataDirectory.exists()) {
            log.warn("Data directory will be created since it does not yet exist");
            this.dataDirectory.mkdir();
        }
        this.checkDataDirectory();
    }

    public String create(final Visa visa) throws IOException {

        osgiDummyService.doStuff();

        /* make sure that the data directory is still there */
        this.checkDataDirectory();

        /* Create and set a visa id for further referencing of the visa */
        final String id = UUID.randomUUID().toString();
        visa.setId(id);

        /* Create the target file for writring the json to */
        final File jsonFile = new File(dataDirectory, "visa-" + id + ".json");
        try (OutputStream sink = new FileOutputStream(jsonFile)) {
            serializationService.toJson(visa, sink);
        }
        log.info("Succesfully saved application {}", id);
        return visa.getId();
    }

    private void checkDataDirectory() {
        if (!this.dataDirectory.isDirectory()) {
            throw new IllegalStateException("Data directory " + dataDirectoryPath + " is not a valid directory");
        }
        if (!this.dataDirectory.canWrite()) {
            throw new IllegalStateException("Unable to create data directory " + dataDirectoryPath);
        }
    }

    public Visa retrieve(final String id) throws IOException {
        final File jsonFile = new File(dataDirectoryPath, "visa-" + id + ".json");
        return serializationService.fromJson(new FileInputStream(jsonFile), Visa.class);
    }

    @Override
    public List<Visa> list() throws IOException {
        this.checkDataDirectory();
        final String[] visaFilePaths = dataDirectory.list((File dir, String name) -> name.startsWith("visa-") && name.endsWith(".json"));
        final List<Visa> visas = new ArrayList<>();
        for (final String path : visaFilePaths) {
            try (final InputStream src = new FileInputStream(new File(dataDirectory, path))) {
                visas.add(serializationService.fromJson(src, Visa.class));
            }
        }
        return visas;
    }
}
