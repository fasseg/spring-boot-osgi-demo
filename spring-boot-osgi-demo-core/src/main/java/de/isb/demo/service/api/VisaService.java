package de.isb.demo.service.api;

import de.isb.demo.model.Visa;

import java.io.IOException;
import java.util.List;

public interface VisaService {
    String create(Visa visa) throws IOException;
    Visa retrieve(String id) throws IOException;
    List<Visa> list() throws IOException;
}
