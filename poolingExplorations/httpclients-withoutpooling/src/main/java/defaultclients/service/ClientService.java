package defaultclients.service;

import defaultclients.model.PerformanceStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class ClientService {

    @Autowired
    HttpClient httpClient;

    private String remoteHost = "performancelogger";

    private int remotePort = 8080;

    private static final String API_NAME="performanceStatsGet";

    private String remoteUrl = new StringBuilder("http://")
            .append(remoteHost).append(":")
            .append(remotePort).append("/")
            .append(API_NAME).toString();

    public PerformanceStatistics getRemotePerformanceStats() throws IOException {
        return httpClient.sendHttpRequest(remoteUrl,null);
    }
}
