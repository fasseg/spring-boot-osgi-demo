package de.isb.demo.rest;

import de.isb.demo.model.Visa;
import de.isb.demo.model.rest.CreateVisaResponse;
import de.isb.demo.model.rest.ErrorResponse;
import de.isb.demo.model.rest.RestResponse;
import de.isb.demo.service.api.VisaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
public class VisaController {

    private static final Logger log = LoggerFactory.getLogger(VisaController.class);

    @Autowired
    private VisaService visaService;

    @RequestMapping(value = "/visa", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CreateVisaResponse create(@RequestBody final Visa visa) throws IOException {
        final String id = visaService.create(visa);
        return CreateVisaResponse.ok(id);
    }

    @RequestMapping(value = "/visa/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Visa retrieve(@PathParam("id") final String id) throws IOException {
        return visaService.retrieve(id);
    }

    @RequestMapping(value = "/visa-list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Visa> list() throws IOException {
        return visaService.list();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = FileNotFoundException.class)
    public RestResponse handleFileNotFoundException(FileNotFoundException e) {
        log.error("Unable to find target file", e);
        return ErrorResponse.error("Unable to find resource: " + e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = IOException.class)
    public RestResponse handleFileIOException(FileNotFoundException e) {
        log.error("I/O Error occurred: ", e);
        return ErrorResponse.error("Unable to find resource: " + e.getMessage());
    }
}
