package defaultclients.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import defaultclients.model.PerformanceStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class HttpClient {

    @Autowired
    private RestTemplate restTemplate;

    private static final String clientAppName = "withPooling";
    private static final ObjectMapper MAPPER_READER = new ObjectMapper();

    public <T, V> PerformanceStatistics sendHttpRequest(String remoteUrl,
                                                        Map<String, String> additionalHeaders) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<MediaType> acceptedTypes = new ArrayList<MediaType>();
        acceptedTypes.add(MediaType.APPLICATION_JSON);
        headers.setAccept(acceptedTypes);
        headers.set("ApplicationName", clientAppName);
        if (additionalHeaders != null) {
            headers.setAll(additionalHeaders);
        }


        ResponseEntity<String> responseEntity = null;
        try {
            long timeStampBeforeCall = System.currentTimeMillis();
            responseEntity = restTemplate.exchange(remoteUrl, HttpMethod.GET, null, String.class);
            long timeTakenInMilliSecs = System.currentTimeMillis() - timeStampBeforeCall;
            System.out.println("Time taken in milliseconds to serve " + remoteUrl + " = " + timeTakenInMilliSecs);


            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                //Do nothing
            } else {

                throw new RuntimeException("request unsuccessful: "
                        + responseEntity.getStatusCode());
            }
        } catch (RestClientException e) {

            throw e;
        }

        return (PerformanceStatistics) unMarshalJsonToObj(responseEntity.getBody());
    }

    public Object unMarshalJsonToObj(String json) throws IOException {

        InputStream inputStream = null;
        try {

            inputStream = new ByteArrayInputStream(json.getBytes());
            JsonNode jsonNode = MAPPER_READER.readTree(inputStream);
            return MAPPER_READER.treeToValue(jsonNode, PerformanceStatistics.class);

        } catch (Exception exception) {
            throw new RuntimeException("Failed to unmarshall json" + json + "to the object due to " + exception);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }

        }
    }
}


