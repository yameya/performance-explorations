package defaultclients.controller;

import defaultclients.model.PerformanceStatistics;
import defaultclients.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @RequestMapping(value = "/getRemotePerformanceStats", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public PerformanceStatistics getRemotePerformanceStats() throws IOException {
        return clientService.getRemotePerformanceStats();
    }



}
