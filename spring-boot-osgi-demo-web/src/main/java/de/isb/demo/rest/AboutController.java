package de.isb.demo.rest;

import de.isb.demo.model.rest.AboutResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AboutController {

    @Value("${demo.application.name}")
    private String applicationName;

    @Value("${demo.application.version}")
    private String applicationVersion;

    @RequestMapping(value="/about", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AboutResponse retrieveAbout() {
        return new AboutResponse(applicationName, applicationVersion);
    }

}
