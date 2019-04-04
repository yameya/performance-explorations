package defaultclients.init;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@EnableAutoConfiguration
@ComponentScan("defaultclients")
public class Application {

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public RestTemplate restTemplate() throws Exception {
        RestTemplate restTemplate;
        restTemplate = new RestTemplate(getClientHttpRequestFactory());
        return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory getClientHttpRequestFactory() throws Exception {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(
                getHttpClient());
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(5000);
        return factory;
    }

    HttpClient getHttpClient() throws Exception {

        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.getSocketFactory()).build();
        PoolingHttpClientConnectionManager poolingManager = new PoolingHttpClientConnectionManager(
                registry);
        poolingManager.setMaxTotal(25);
        poolingManager.setDefaultMaxPerRoute(25);

        return HttpClients.custom().setConnectionManager(poolingManager).setKeepAliveStrategy(getKeepAliveStreategy()).build();
    }

    private ConnectionKeepAliveStrategy getKeepAliveStreategy() {
        return new ConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                return 10000;
            }
        };
    }

}